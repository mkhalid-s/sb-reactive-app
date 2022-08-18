package com.khalid.sbreactive.app.service;

import com.khalid.sbreactive.app.exception.InputNumberValidationException;
import com.khalid.sbreactive.app.modal.MathResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MathService {

    public Mono<MathResponse> getSquare(int intNum) {
        return Mono.fromSupplier(() -> intNum * intNum).map(MathResponse::new);
    }

    public Flux<MathResponse> getMultiplicationTable(int inNum) {
        return Flux.range(1, 10).handle((integer, synchronousSink) -> {
                    if (inNum > 1000)
                        synchronousSink.error(new InputNumberValidationException(String.format("Input Supplied is %d ", inNum)));
                    else
                        synchronousSink.next(integer);
                })
                .cast(Integer.class)
                .map(in -> in * inNum)
                .map(MathResponse::new);
    }
}
