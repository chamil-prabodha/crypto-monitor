package com.zooplus.cryptomonitor.service;

import com.zooplus.cryptomonitor.exception.RestAPIException;

import java.util.List;

public interface CurrencyService <T> {
    List<T> getCurrencyList() throws RestAPIException;
    T getPrice(T currency, String vsCurrency) throws RestAPIException;
}
