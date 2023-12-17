package com.edd.date.config.security;

import com.edd.date.constants.WebConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class CipherConfig {
    @Value("${token.secretKey}")
    private String secretKey;


    public String encrypt(String str) throws Exception {
        byte[] messageBytes = str.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), WebConstants.ALGORITHM);
        Cipher cipher = Cipher.getInstance(WebConstants.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(messageBytes));
    }




}
