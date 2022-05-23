package com.zooplus.cryptomonitor.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.zooplus.cryptomonitor.model.CryptoCurrency;
import com.zooplus.cryptomonitor.model.DetailedCryptoCurrency;

import java.util.Currency;

public class CryptoResponseTransformer {
    private CryptoResponseTransformer() {}

    public static DetailedCryptoCurrency getDetailedCryptoCurrency(CryptoCurrency cryptoCurrency, String vsCurrency, JsonNode jsonNode) {
        if (jsonNode != null && jsonNode.get(cryptoCurrency.getId()) != null) {
            if (jsonNode.get(cryptoCurrency.getId()).get(vsCurrency) != null) {
                Currency currency = CurrencyUtil.getCurrency(vsCurrency);
                DetailedCryptoCurrency detailedCryptoCurrency = new DetailedCryptoCurrency(cryptoCurrency);
                detailedCryptoCurrency.setPrice(jsonNode.get(cryptoCurrency.getId()).get(vsCurrency).decimalValue());
                detailedCryptoCurrency.setCurrencyName(currency.getDisplayName());
                detailedCryptoCurrency.setCurrencySymbol(currency.getSymbol());
                return detailedCryptoCurrency;
            }
        }
        return null;
    }

    public static <T> T getOrDefault(T actualValue, T defaultValue) {
        if (actualValue == null) {
            return defaultValue;
        }
        return actualValue;
    }
}
