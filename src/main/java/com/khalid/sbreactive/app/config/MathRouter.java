package com.khalid.sbreactive.app.config;

import com.khalid.sbreactive.app.exception.InputNumberValidationException;
import com.khalid.sbreactive.app.modal.ExceptionResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class MathRouter {

    private final MathHandler mathHandler;

    public MathRouter(MathHandler mathHandler) {
        this.mathHandler = mathHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> mainRouter() {
        return RouterFunctions
                .route().path("router", this::routerFunction)
                .build();
    }

    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .GET("math/square/{num}", mathHandler::getSquare)
                .GET("math/table/{num}", mathHandler::getNumTable)
                .onError(InputNumberValidationException.class, serverRequestExceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> serverRequestExceptionHandler() {
        return (throwable, serverRequest) -> {
            InputNumberValidationException validationException = (InputNumberValidationException) throwable;
            ExceptionResponse exceptionResponse = new ExceptionResponse(InputNumberValidationException.getErrCode(), validationException.getMessage(), validationException.getResponseMsg());
            return ServerResponse.badRequest().bodyValue(exceptionResponse);
        };

    }
}
