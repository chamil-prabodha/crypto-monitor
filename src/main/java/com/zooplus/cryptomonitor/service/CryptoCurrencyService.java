package com.zooplus.cryptomonitor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.zooplus.cryptomonitor.exception.CryptoAPIException;
import com.zooplus.cryptomonitor.model.CryptoCurrency;
import com.zooplus.cryptomonitor.model.DetailedCryptoCurrency;
import com.zooplus.cryptomonitor.util.CryptoResponseTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CryptoCurrencyService implements CurrencyService<CryptoCurrency> {
    private final RestTemplate restTemplate;
    private final URL baseUrl;

    @Value("${crypto.client.url.endpoint.coins}")
    private String coinsEndpoint;
    @Value("${crypto.client.url.endpoint.price}")
    private String priceEndpoint;

    @Autowired
    public CryptoCurrencyService(RestTemplate restTemplate, @Value("${crypto.client.url.basePath}") String basePath) throws MalformedURLException {
        this.restTemplate = restTemplate;
        this.baseUrl = new URL(basePath);
    }

    public  List<CryptoCurrency> getCurrencyList() throws CryptoAPIException {
        List<CryptoCurrency> cryptoCurrencyList;
        try {
            URL url = new URL(baseUrl, coinsEndpoint);
            ResponseEntity<List<CryptoCurrency>> currencyListResponse = restTemplate.exchange(url.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            cryptoCurrencyList = CryptoResponseTransformer.getOrDefault(currencyListResponse.getBody(), new ArrayList<>());
            log.info("successfully fetched currency list with {} currencies", cryptoCurrencyList.size());
            return cryptoCurrencyList;
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                log.error("received error response: {}", ((HttpClientErrorException) e).getResponseBodyAsString());
            }
            log.error("an exception occurred while fetching currency list", e);
            throw new CryptoAPIException("Crypto Currency API for listing currencies is not available", HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public CryptoCurrency getPrice(CryptoCurrency cryptoCurrency, String vsCurrency) throws CryptoAPIException {
        try {
            URIBuilder builder = new URIBuilder(baseUrl.toString()).setPath(priceEndpoint)
                    .addParameter("ids", cryptoCurrency.getId())
                    .addParameter("vs_currencies", vsCurrency);
            log.debug("attempting to make request: {}", builder.toString());
            ResponseEntity<JsonNode> response = restTemplate.exchange(builder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            log.info("successfully fetched price of currency {} in {}", cryptoCurrency, vsCurrency);
            return CryptoResponseTransformer.getDetailedCryptoCurrency(cryptoCurrency, vsCurrency, response.getBody());
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                log.error("received error response: {}", ((HttpClientErrorException) e).getResponseBodyAsString());
            }
            log.error("an exception occurred while fetching the price of currency {} in {}", cryptoCurrency, vsCurrency, e);
            throw new CryptoAPIException("Crypto Currency API for getting price is not available", HttpStatus.BAD_REQUEST, e.getMessage(), new DetailedCryptoCurrency(cryptoCurrency));
        }
    }
}
