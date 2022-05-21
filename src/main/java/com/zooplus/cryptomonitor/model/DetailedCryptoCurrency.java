package com.zooplus.cryptomonitor.model;

import java.math.BigDecimal;

public class DetailedCryptoCurrency extends CryptoCurrency {
    private BigDecimal price;
    private String currencySymbol;
    private String currencyName;

    public DetailedCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.setId(cryptoCurrency.getId());
        this.setSymbol(cryptoCurrency.getSymbol());
        this.setName(cryptoCurrency.getName());
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
