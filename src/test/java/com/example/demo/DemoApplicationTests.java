package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.services.AESEncryptionService;
import com.example.demo.services.EncDecService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void givenString_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {


        String input = "273 and duong vuong";
        SecretKey key = AESEncryptionService.generateKey(128);
        String testEncodeKey = "bwHbRQzXAZywp7rJ4Iq9lw==";
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(testEncodeKey);
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        IvParameterSpec ivParameterSpec = AESEncryptionService.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AESEncryptionService.encrypt(algorithm, input, originalKey, ivParameterSpec);
        String testEncryptedText = "gi6j5lV87CWPSZLXYNj/QGIncNdP57HHxTeRb6zX0CQ=";

        String plainText = AESEncryptionService.decrypt(algorithm, testEncryptedText, originalKey, ivParameterSpec);
        String test = "";
        Assertions.assertEquals(input, plainText);
    }


    @Test
    void AESStringKeyConver() throws NoSuchAlgorithmException {
        // create new key
         SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();


        // get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        String testEncodeKey = "6d792d7365637572652d6b65792d7370656336be20e6d280839855cedfc6cb2d";
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(testEncodeKey);
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

    }
    @Test
    void  RSARestoreKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        EncDecService  encDecService = new EncDecService();
        encDecService.init();
        String input="test";
        String encrypted = encDecService.encrypt(input);
        String privateKeyBase64 = Base64.getEncoder().encodeToString(encDecService.privateKey.getEncoded());
        // Step 3: Convert Base64 String back to Private Key
        byte[] decodedPrivateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKeyBytes);
        PrivateKey restoredPrivateKey = keyFactory.generatePrivate(privateKeySpec);
        String text = encDecService.decrypt(encrypted,restoredPrivateKey);
    }
}
