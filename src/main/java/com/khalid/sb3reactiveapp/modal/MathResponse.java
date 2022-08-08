package com.khalid.sb3reactiveapp.modal;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MathResponse {

    private int result;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public MathResponse(int result) {
        this.result = result;
    }
}
