package com.khalid.sb3reactiveapp.controller;

import com.khalid.sb3reactiveapp.modal.MathResponse;
import com.khalid.sb3reactiveapp.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/")
    public String rootPoint(){
        return """ 
                  Nice Try! :)
                  This is root REST Endpoint. Try to append specific path. \\__/
               """;
    }

    @GetMapping("/square/{num}")
    public Mono<MathResponse> findSquareOfNum(@PathVariable int num){
        return this.mathService.getSquare(num);
    }
}
