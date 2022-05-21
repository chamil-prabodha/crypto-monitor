package com.zooplus.cryptomonitor.controller;

import com.zooplus.cryptomonitor.model.CryptoCurrency;
import com.zooplus.cryptomonitor.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CryptoController {
    @Autowired
    private CryptoCurrencyService cryptoClientService;

    @GetMapping("/currency/list")
    public ResponseEntity<List<CryptoCurrency>> getCoins() {
        List<CryptoCurrency> cryptoCurrencyList = cryptoClientService.getCurrencyList();
        return new ResponseEntity<>(cryptoCurrencyList, HttpStatus.OK);
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<CryptoCurrency> getPrice(@PathVariable String id) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(id);
        CryptoCurrency response = cryptoClientService.getPrice(cryptoCurrency, "lkr");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
