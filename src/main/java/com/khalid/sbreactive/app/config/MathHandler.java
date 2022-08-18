package com.khalid.sbreactive.app.config;

import com.khalid.sbreactive.app.exception.InputNumberValidationException;
import com.khalid.sbreactive.app.modal.MathResponse;
import com.khalid.sbreactive.app.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MathHandler {

    private final MathService mathService;

    @Autowired
    public MathHandler(MathService mathService) {
        this.mathService = mathService;
    }

    public Mono<ServerResponse> getSquare(ServerRequest serverRequest) {
        int num = Integer.parseInt(serverRequest.pathVariable("num"));
        Mono<MathResponse> mathResponseMono = mathService.getSquare(Math.toIntExact(num));
        return mathResponseMono
                .flatMap(mathResponse -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(mathResponse, MathResponse.class)
                        .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> getNumTable(ServerRequest serverRequest) {
        int num = Integer.parseInt(serverRequest.pathVariable("num"));
        if (num > 1000) {
            return Mono.error(new InputNumberValidationException(String.format("Input Supplied is %d ", num)));
        }
        Flux<MathResponse> mathResponseFlux = mathService.getMultiplicationTable(num);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(mathResponseFlux, MathResponse.class);
    }
}
