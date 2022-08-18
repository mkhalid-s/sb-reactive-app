package com.khalid.sbreactive.app.controller;

import com.khalid.sbreactive.app.modal.MathResponse;
import com.khalid.sbreactive.app.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/")
    public String rootPoint() {
        return """ 
                   Nice Try! :)
                   This is root REST Endpoint. Try to append specific path. \\__/
                """;
    }

    @GetMapping(value = "/square/{num}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<MathResponse> findSquareOfNum(@PathVariable int num) {
        return this.mathService.getSquare(num);
    }

    @GetMapping(value = "/table/{num}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MathResponse> getMultiplyTable(@PathVariable int num) {
        return this.mathService.getMultiplicationTable(num);
    }

    @GetMapping(value = "/table/{num}/assignment", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MathResponse> getMultiplyTableAssignment(@PathVariable int num) {
        return Flux.just(num)
                .filter(integer -> integer <= 1000 && integer > 0)
                .flatMap(integer -> this.mathService.getMultiplicationTable(num));
        //.switchIfEmpty(Subscriber::onComplete);
    }
}
