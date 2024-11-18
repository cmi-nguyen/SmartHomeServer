package com.example.demo.services;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

@Service
public class EncDecService
{
    private static final String RSA = "RSA";

    public PrivateKey privateKey ;
    public PublicKey publicKey ;

    public void init() throws NoSuchAlgorithmException, IOException {
        KeyPair  keyPair = generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
//        String filePath = "C:/Projects/ATBM//SpringBootTest-main/src/main/resources/mykey.pem";

        // Write private key to PEM file
//        writePemFile(privateKey, "RSA PRIVATE KEY", filePath);
    }
    public void RestoreKey(){

    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }


    public String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        //Generate Key Pair
        //Initialize the private key and public
        init();

        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedValue  = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);

    }

    public String decrypt(String encryptedData, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decryptedValue  = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
        return new String(decryptedValue);

    }
}