package com.example.demo.services;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class KeyUtils {
    // Convert Key to Base64 encoded String
    public static String encodeKeyToBase64(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Decode Base64 encoded String back to Key (for AES)
    public static SecretKey decodeAESKeyFromBase64(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, "AES");
    }
}
