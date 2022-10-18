package com.example.finalproject.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProfileCred {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
