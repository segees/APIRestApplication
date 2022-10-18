package com.example.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorException extends Exception{

    private String error;


}
