package com.zooplus.cryptomonitor.controller;

import com.zooplus.cryptomonitor.model.Currency;
import com.zooplus.cryptomonitor.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CryptoController {
    @Autowired
    private CryptoCurrencyService cryptoClientService;

    @GetMapping("/currency/list")
    public ResponseEntity<List<Currency>> getCoins() {
        List<Currency> currencyList = cryptoClientService.getCurrencyList();
        return new ResponseEntity<>(currencyList, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<String> getPrice() {
        Currency currency = new Currency();
        currency.setId("bitcoin");
        String response = cryptoClientService.getPrice(currency, "usd", String.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
