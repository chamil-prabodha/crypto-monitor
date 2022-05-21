package com.zooplus.cryptomonitor.model;

import java.math.BigDecimal;

public class DetailedCurrency extends Currency {
    private BigDecimal price;
    private String currencySymbol;

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
}
