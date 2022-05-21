package com.zooplus.cryptomonitor.service;

import java.util.List;

public interface CurrencyService <T> {
    List<T> getCurrencyList();
    <S> S getPrice(T currency, String vsCurrency, Class<S> responseType);
}
