package com.sanlea.study.scg.router;

import com.sanlea.study.scg.gwfilter.CheckProductGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hao123 configuration
 *
 * @author kut
 */
@Configuration
public class Hao123RouterConfiguration {
    @Bean("routes.hao123")
    public RouteLocator routes(RouteLocatorBuilder builder, CheckProductGatewayFilterFactory filterFactory) {
        var config = new CheckProductGatewayFilterFactory.Config();
        config.setName("ABCD");
        return builder.routes().route(
                "routes.hao123",
                spec -> spec.path("/hao123/**")
                        .filters(f -> f.stripPrefix(1).filter(filterFactory.apply(config)))
                        .uri("http://www.hao123.com")
        ).build();
    }
}
