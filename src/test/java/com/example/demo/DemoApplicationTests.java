package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.services.AESEncryptionService;
import com.example.demo.services.EncDecService;
import com.example.demo.services.HybridCryptosystem;
import com.example.demo.services.KeyUtils;
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
import static org.assertj.core.api.Assertions.byLessThan;

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
    @Test
    void TestDecrypt() throws Exception {
        String EncryptedMessage = "oBv+Om9g/coyIJA6Nm7K4w==";
        String AESEncryptedKey = "GfjHnHKp44r2r/yWLzjaHtLhdn/Od24wBWoVxX0mkxgvEcdVaj6AVbD0AEHZHXhrwTJnhdHLtKOAnF9JSYACn5tlnLjn5MtgUrlk90idJFSvcgg6emff1rN6FTh6hJpiLS8Fa7kT/qIhlMilBMxorN/dUEG/2sYgqMh395MSBsF3VU1PT8TyJzJwiK5ga7Q3abnw8fVqdCR2HdaJSPoEQo7nilRz2Vmhqc2ZPU+g3R0bFhbr34GL84SwtgM5uBviP8rcZwM8aZhpQB2VLIODGNzTZhLtg12ZpLtAPFt0a0hNFttkN0J5HVKm3rv83i/xXhW97RLPMeLBsE4nenygNg==";
        String RSAKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDMUqiK/PawYmnusrUk3aikbn0+6iMih41foQOrcXjjh9KDPYvX6sDjMyqUo0nCW//s+bTjMdArR/yOQxrtlLKBhGSD9OarWGKmD5fqLWSe1EMAmO3imNzr4f8JdwgDIQPYblmes2iIzZraRti2X/TPKg85X5eDIqCBT+oEGev4DATCy92ZL0ccNxwuhrMVjJpHdjGQVIt6YWXeo6V0c/n9eNRmw/Ekax7945ETDkkHpWNFF04AsQZlrEt3TflEigbwvu/y4ZzEkwl6axJEi/C5RlTYEMhMrdm3nUYVS9z8HmyQi26Ct5zAmJmz95eUDVxz4bIJAKlMhADelJ/XpzORAgMBAAECggEAHgOscj0GaKhkTaYJ2cBdTsj+SVjapW61KU2Sp5jT0dS4wK0GAcdLOMo3iz3QuXuLRP6fgFpnwoaLcUMmowIwwLmNNxx+1Wp2lKn+E8PsSoVS0bUlXh/ndW0tlPCGiEh4HfnZMhNtG9+Gy7a+4feG6PcRj8jc4xdvQt+q4u8DN1zFXTJkboK3Dw6jLrtIIs9jBUsU8ozupig69UdzobYgrX8I/geFgocG3nhLfldK8tgcwx9+qGLA99gTtj6yaVQCwLXooZ/NgJ724Kn7WxcXvSbsMCCKHb1u+uYDPwuBDIFWgEpfAnSiuxx7tF3PiiOaWDgGJWQEx0pHQRFz6E27UwKBgQDt9uIr67Cz8S9kAhEYr7zfvbHUgbTEvBaO4mpQ1nZu0pKLsk8Vij8APdxKtSZ/WkbmpR7zZt8M2M4kDHwrYixYd7RCFPN695FXoaLI3ydQGEGi+faotzVyO9C8OzzpbxEZrL1qbq/kHfL5pN3FRE7UhWXcbKAi8jGhtC5XOHSlrwKBgQDbzws48AJLKiJV4uv5UVgkh/uR2vSta+bshQV+MWN69zal42zn6+9/thwh43462wL6flfHUrMLRDN7/ddrc3WrVWVkIMdBpoZ5fMDSpnInW1Opiel4605cndl3iIAT32LyruHikZz16cKQ5sUoLlLswNRrH5RDRa5OUvRu5LVKvwKBgEcvhKco4Xpi2w9qwtyErdass4bjqkT0Ezd5Kd622UT7vxUFKT1DVudmmgDLQhH02qLioGilzHHWZYxJI9gCjUuFu6nNBK0zZAo38y9t+h+usevkJNKBw/5BQIcenXEjZZUtpJgzNnDiOK5PkHwTnBVdNoSDBkCN/STndrlmzRZHAoGBAJL78FUyqVX55tvn0kP10Sscg20VLWiKLvEaQoIQJKWlijWDzt+HNbAA5u8CYPaaXXOD6M6wzmlKQeM360U/AACCKjq6cIPGB0ZV1dsG0m7e8yL7xdPGBbCJHVKBRKQFTPVbXFFVbdBDcIfUySIFWA5ZI7I+e7ZAcj6N2kYNSe2hAoGAR0LjvgA0+pyIJeIl3rBI6i/vxHQ744Xml7VSY1siFBmEGbdh5bAn2mu936mPBJm33r1/z55mP3w9p1FPc9uINyXpG0VYyu9xoQgEsevc5In6XQNKg1rLXWFyluGLHbkCaIria+Lw77XxQe1OvUngXOV2325s9mALRewfvmEQfd4=";
        // Step 1: Decode the RSA private key
        PrivateKey privateKey = decodePrivateKeyFromBase64(RSAKeyString);

        // Step 2: Decrypt the AES key using the RSA private key
        byte[] encryptedAESKeyBytes = Base64.getDecoder().decode(AESEncryptedKey);
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAESKeyBytes);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // Step 3: Decrypt the message using the AES key
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(EncryptedMessage);
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedMessageBytes = aesCipher.doFinal(encryptedMessageBytes);

        // Print the decrypted message
        String decryptedMessage = new String(decryptedMessageBytes);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
    public static PrivateKey decodePrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
    }

    @Test
    void HybribCryptoSystemTest(){
        try {
            // Step 1: Generate RSA keys for Alice

            KeyPair rsaKeyPair = HybridCryptosystem.generateRSAKeyPair();
            PublicKey rsaPublicKey = rsaKeyPair.getPublic();
            PrivateKey rsaPrivateKey = rsaKeyPair.getPrivate();

            // Step 2: Generate AES key for Bob
            SecretKey aesKey = HybridCryptosystem.generateAESKey();

            // Step 3: Encrypt data using AES
            String data = "Chuc mung 20/11";
            byte[] encryptedData = HybridCryptosystem.encryptAES(data.getBytes(), aesKey);

            // Step 4: Encrypt AES key using RSA
            byte[] encryptedAESKey = HybridCryptosystem.encryptRSA(aesKey.getEncoded(), rsaPublicKey);

            // Simulate transmission of encrypted data and key
            System.out.println("Encrypted Data: " + Base64.getEncoder().encodeToString(encryptedData));
            System.out.println("Encrypted AES Key: " + Base64.getEncoder().encodeToString(encryptedAESKey));
            byte [] RSAKeyBase = rsaPrivateKey.getEncoded();
            System.out.println("RSA String: "+Base64.getEncoder().encodeToString(RSAKeyBase));
            // RSA Key from base
            PrivateKey testKey = KeyUtils.decodePrivateKeyFromBase64(Base64.getEncoder().encodeToString(RSAKeyBase));

            // Step 5: Decrypt AES key using RSA
            byte[] decryptedAESKeyBytes = HybridCryptosystem.decryptRSA(encryptedAESKey, testKey);
            SecretKey decryptedAESKey = new SecretKeySpec(decryptedAESKeyBytes, "AES");

            // Step 6: Decrypt data using AES
            byte[] decryptedData = HybridCryptosystem.decryptAES(encryptedData, decryptedAESKey);
            System.out.println("Decrypted Data: " + new String(decryptedData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
