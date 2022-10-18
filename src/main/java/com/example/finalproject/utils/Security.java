package com.example.finalproject.utils;


import com.example.finalproject.classes.ProfileLoggedCreds;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Security {
    //SHA-512
    public static ProfileLoggedCreds hashPassword(String password) {
        ProfileLoggedCreds profileLoggedCreds;
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            MessageDigest md = null;
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return new ProfileLoggedCreds(md.digest(password.getBytes(StandardCharsets.UTF_8)), salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean checkPassword(String password,byte[] hashedPassword, byte[] salt) {

        try {

            MessageDigest md = null;
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedEnteredPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            if(Arrays.equals(hashedEnteredPassword,hashedPassword)) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
