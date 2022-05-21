package com.zooplus.cryptomonitor.service;

import com.zooplus.cryptomonitor.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CryptoCurrencyService implements CurrencyService<Currency> {
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

    public  List<Currency> getCurrencyList() {
        List<Currency> currencyList = new ArrayList<>();
        try {
            URL url = new URL(baseUrl, coinsEndpoint);
            ResponseEntity<Currency[]> currencyListResponse = restTemplate.getForEntity(url.toString(), Currency[].class);
            if (currencyListResponse.getBody() != null) {
                currencyList = Arrays.asList(currencyListResponse.getBody());
                log.info("successfully fetched currency list with {} currencies", currencyList.size());
            }
        } catch (Exception e) {
            log.error("an exception occurred while fetching currency list", e);
        }
        return currencyList;
    }

    @Override
    public <S> S getPrice(Currency currency, String vsCurrency, Class<S> responseType) {
        try {
            URIBuilder builder = new URIBuilder(baseUrl.toString()).setPath(priceEndpoint)
                    .addParameter("ids", currency.getId())
                    .addParameter("vs_currencies", vsCurrency);
            S response = restTemplate.getForObject(builder.toString(), responseType);
            log.info("successfully fetched price of currency {} in {}", currency, vsCurrency);
            return response;
        } catch (Exception e) {
            log.error("an exception occurred while fetching the price of currency {} in {}", currency, vsCurrency, e);
        }
        return null;
    }
}
