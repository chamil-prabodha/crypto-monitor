package com.zooplus.cryptomonitor.controller;

import com.zooplus.cryptomonitor.exception.RestAPIException;
import com.zooplus.cryptomonitor.model.CryptoCurrency;
import com.zooplus.cryptomonitor.model.IpInfo;
import com.zooplus.cryptomonitor.model.response.APIResponse;
import com.zooplus.cryptomonitor.service.CurrencyService;
import com.zooplus.cryptomonitor.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CryptoController {
    private final CurrencyService<CryptoCurrency> cryptoCurrencyService;
    private final IpInfoService<IpInfo> ipInfoService;

    @Autowired
    public CryptoController(CurrencyService<CryptoCurrency> cryptoCurrencyService, IpInfoService<IpInfo> ipInfoService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.ipInfoService = ipInfoService;
    }

    @GetMapping("/currency/list")
    public ResponseEntity<APIResponse<List<CryptoCurrency>>> getCoins() throws RestAPIException {
        List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyService.getCurrencyList();
        APIResponse<List<CryptoCurrency>> apiResponse = new APIResponse<>(true, cryptoCurrencyList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<APIResponse<CryptoCurrency>> getPrice(@PathVariable String id, @RequestParam String ip) throws RestAPIException {
        IpInfo ipInfo = ipInfoService.getInfo(ip);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(id, null, null);
        CryptoCurrency detailedCryptoCurrency = cryptoCurrencyService.getPrice(cryptoCurrency, ipInfo.getCurrency().toLowerCase());
        APIResponse<CryptoCurrency> apiResponse = new APIResponse<>(true, detailedCryptoCurrency);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
