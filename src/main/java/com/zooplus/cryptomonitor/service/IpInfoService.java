package com.zooplus.cryptomonitor.service;

import com.zooplus.cryptomonitor.exception.CryptoAPIException;

public interface IpInfoService<T> {
    T getInfo(String ip) throws CryptoAPIException;
}
