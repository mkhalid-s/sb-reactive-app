package com.khalid.sb3reactiveapp.service;

import com.khalid.sb3reactiveapp.modal.MathResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class MathService {

    public Mono<MathResponse> getSquare(int intNum) {
        return Mono.fromSupplier(() -> intNum * intNum).map(MathResponse::new);
    }

    public Flux<MathResponse> getMultiplicationTable(int inNum){
        return Flux.range(1 , 10)
                .map(in -> in * inNum)
                .map(MathResponse::new);
    }
}
