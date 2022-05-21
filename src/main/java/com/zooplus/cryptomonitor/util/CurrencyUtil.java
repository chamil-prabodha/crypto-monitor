package com.zooplus.cryptomonitor.util;

import java.util.Currency;

public class CurrencyUtil {
    private CurrencyUtil() {}

    public static Currency getCurrency(String id) {
        Currency currency = null;
        if (id != null) {
            currency = Currency.getInstance(id.toUpperCase());
        }
        if (currency != null) {
            return currency;
        }
        return Currency.getInstance("USD");
    }
}
