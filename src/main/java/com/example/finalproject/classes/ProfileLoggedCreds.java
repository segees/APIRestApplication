package com.example.finalproject.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileLoggedCreds {
    private byte[] password;
    private byte[] salt;
}
