package com.sanlea.study.scg.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Hao123 function configuration
 *
 * @author kut
 */
@Configuration
public class Hao123FunctionConfiguration {
    @Bean("functions.hao123.order")
    public RouterFunction<ServerResponse> orderHandlerRouting() {
        return RouterFunctions.route(GET("/hao123/order"), request -> ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("show me the money"))
        ).filter((request, next) -> {
            if (request.headers().firstHeader("OOXX") == null) {
                return ServerResponse.status(403)
                        .body(BodyInserters.fromValue("No permission"));
            } else {
                return next.handle(request);
            }
        });
    }
}
