package com.zooplus.cryptomonitor.service;

import java.util.List;

public interface CurrencyService <T> {
    List<T> getCurrencyList();
    T getPrice(T currency, String vsCurrency);
}
