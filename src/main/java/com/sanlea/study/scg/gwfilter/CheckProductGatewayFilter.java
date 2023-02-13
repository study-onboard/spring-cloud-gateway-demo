package com.sanlea.study.scg.gwfilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;

import java.nio.charset.StandardCharsets;

/**
 * Check product gateway filter
 *
 * @author kut
 */
public class CheckProductGatewayFilter implements GatewayFilter {

    private final CheckProductGatewayFilterFactory.Config config;

    public CheckProductGatewayFilter(CheckProductGatewayFilterFactory.Config config) {
        this.config = config;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getHeaders().getFirst(config.getName()) == null) {
            String message = "Show me the money, and how do you turn this on?";
            var data = message.getBytes(StandardCharsets.UTF_8);

            var response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
            return response.writeAndFlushWith(Flux.just(
                    ByteBufFlux.just(response.bufferFactory().wrap(data))
            ));
        }
        return chain.filter(exchange);
    }
}
