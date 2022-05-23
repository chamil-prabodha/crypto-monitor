package com.zooplus.cryptomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfo {
    private String ip;
    private String currency;
    private String currencyName;
    private boolean error;
    private String reason;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "IpInfo{" +
                "ip='" + ip + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", error=" + error +
                ", reason='" + reason + '\'' +
                '}';
    }
}
