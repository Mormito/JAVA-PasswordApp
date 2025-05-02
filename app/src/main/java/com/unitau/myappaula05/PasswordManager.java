package com.unitau.myappaula05;

import java.util.Random;
import java.security.*;
import java.util.concurrent.ExecutionException;


public class PasswordManager {

    public String criptografarSenha(String senha) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senha.getBytes());
            byte[] hashBytes = md.digest();

            StringBuilder senhaEn = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    senhaEn.append('0');
                }
                senhaEn.append(hex);
            }
            return senhaEn.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}