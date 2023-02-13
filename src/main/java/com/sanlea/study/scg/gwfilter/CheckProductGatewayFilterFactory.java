package com.sanlea.study.scg.gwfilter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class CheckProductGatewayFilterFactory implements GatewayFilterFactory<CheckProductGatewayFilterFactory.Config> {

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new CheckProductGatewayFilter(config);
    }

    @Data
    public static class Config {
        private String name;
    }
}
