package com.khalid.sb3reactiveapp.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private int errCode;

    private String errMsg;

    private String response;
}
