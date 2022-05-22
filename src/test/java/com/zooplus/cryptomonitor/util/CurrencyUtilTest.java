package com.zooplus.cryptomonitor.util;

import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyUtilTest {

    @Test
    void whenInputIsValid_getCurrencyTest() {
        Currency actualValue = CurrencyUtil.getCurrency("euR");
        assertEquals("EUR", actualValue.getCurrencyCode());
        assertEquals("€", actualValue.getSymbol());
        assertEquals("Euro", actualValue.getDisplayName());
    }

    @Test
    void whenInputIsNotValid_getCurrencyTest() {
        Currency actualValue = CurrencyUtil.getCurrency("asd");
        assertEquals("USD", actualValue.getCurrencyCode());
        assertEquals("$", actualValue.getSymbol());
        assertEquals("US Dollar", actualValue.getDisplayName());
    }

}