package com.khalid.sb3reactiveapp.config;

import com.khalid.sb3reactiveapp.exception.InputNumberValidationException;
import com.khalid.sb3reactiveapp.modal.ExceptionResponse;
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

    @Bean
    public RouterFunction<ServerResponse> routerFunction(MathHandler mathHandler) {
        return RouterFunctions
                .route()
                .GET("/router/math/square/{num}", mathHandler::getSquare)
                .GET("/router/math/table/{num}", mathHandler::getNumTable)
                .onError(InputNumberValidationException.class, serverRequestExceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> serverRequestExceptionHandler(){
        return  (throwable, serverRequest) -> {
            InputNumberValidationException validationException = (InputNumberValidationException) throwable;
            ExceptionResponse exceptionResponse = new ExceptionResponse(InputNumberValidationException.getErrCode(), validationException.getMessage(), validationException.getResponseMsg());
            return ServerResponse.badRequest().bodyValue(exceptionResponse);
        };

    }
}
