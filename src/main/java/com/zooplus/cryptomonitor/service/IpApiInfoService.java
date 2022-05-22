package com.zooplus.cryptomonitor.service;

import com.zooplus.cryptomonitor.model.IpInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class IpApiInfoService implements IpInfoService<IpInfo> {
    private final RestTemplate restTemplate;

    @Value("${ip.client.url.basePath}")
    private String basePath;

    public IpApiInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IpInfo getInfo(String ip) {
        try {
            URIBuilder uriBuilder = new URIBuilder(basePath).setPathSegments(ip, "json");
            log.debug("attempting to make request: {}", uriBuilder.toString());
            ResponseEntity<IpInfo> response = restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, null, IpInfo.class);
            log.debug("received response: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("an exception occurred while getting info for ip {}", ip, e);
        }
        return new IpInfo();
    }
}
