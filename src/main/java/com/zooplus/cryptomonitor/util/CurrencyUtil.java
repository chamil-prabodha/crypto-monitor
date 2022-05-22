package com.zooplus.cryptomonitor.util;

import java.util.Currency;

public class CurrencyUtil {
    private CurrencyUtil() {}

    public static Currency getCurrency(String id) {
        Currency currency = null;
        if (id != null) {
            try {
                currency = Currency.getInstance(id.toUpperCase());
            } catch (Exception ignored) {

            }
        }
        if (currency != null) {
            return currency;
        }
        return Currency.getInstance("USD");
    }
}
