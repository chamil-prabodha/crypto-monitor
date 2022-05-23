package com.zooplus.cryptomonitor.service;

import com.zooplus.cryptomonitor.exception.CryptoAPIException;
import com.zooplus.cryptomonitor.model.CryptoCurrency;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CryptoCurrencyMarketCapService extends CryptoCurrencyService {
    private final RestTemplate restTemplate;
    private final String basePath;

    @Value("${crypto.client.url.endpoint.market}")
    private String marketEndpoint;

    @Autowired
    public CryptoCurrencyMarketCapService(RestTemplate restTemplate, @Value("${crypto.client.url.basePath}") String basePath) throws MalformedURLException {
        super(restTemplate, basePath);
        this.restTemplate = restTemplate;
        this.basePath = basePath;
    }

    @Override
    public List<CryptoCurrency> getCurrencyList() throws CryptoAPIException {
        List<CryptoCurrency> cryptoCurrencyList;
        try {
            URIBuilder builder = new URIBuilder(basePath).setPath(marketEndpoint)
                    .addParameter("vs_currency", "usd")
                    .addParameter("order", "market_cap_desc")
                    .addParameter("per_page", "100")
                    .addParameter("page", "1");
            ResponseEntity<List<CryptoCurrency>> currencyListResponse = restTemplate.exchange(builder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
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
}
