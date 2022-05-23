package com.zooplus.cryptomonitor.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.zooplus.cryptomonitor.TestUtil;
import com.zooplus.cryptomonitor.model.CryptoCurrency;
import com.zooplus.cryptomonitor.model.DetailedCryptoCurrency;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CryptoResponseTransformerTest {

    @Test
    void whenInputIsNotNull_getOrDefaultTest() {
        String input = "input";
        String actualValue = CryptoResponseTransformer.getOrDefault(input, "default");
        assertEquals(input, actualValue);
    }

    @Test
    void whenInputIsNull_getOrDefaultTest() {
        String expectedValue = "default";
        String actualValue = CryptoResponseTransformer.getOrDefault(null, expectedValue);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getDetailedCryptoCurrencyHappyPathTest() throws IOException {
        CryptoCurrency currency = new CryptoCurrency("bitcoin", "btc", "Bit Coin");
        JsonNode jsonNode = TestUtil.getJsonNode("price-response-success-test-input.json", getClass());
        DetailedCryptoCurrency actualValue = CryptoResponseTransformer.getDetailedCryptoCurrency(currency, "usd", jsonNode);
        assertNotNull(actualValue);
        assertEquals("US Dollar", actualValue.getCurrencyName());
        assertEquals("$", actualValue.getCurrencySymbol());
        assertEquals(BigDecimal.valueOf(29421.55), actualValue.getPrice());
        assertEquals("bitcoin", actualValue.getId());
        assertEquals("btc", actualValue.getSymbol());
        assertEquals("Bit Coin", actualValue.getName());
    }

    @Test
    void getDetailedCryptoCurrencyFailureTest() throws IOException {
        CryptoCurrency currency = new CryptoCurrency("bitcoin", "btc", "Bit Coin");
        JsonNode jsonNode = TestUtil.getJsonNode("price-response-failure-test-input.json", getClass());
        DetailedCryptoCurrency actualValue = CryptoResponseTransformer.getDetailedCryptoCurrency(currency, "usd", jsonNode);
        assertNull(actualValue);
    }
}