package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyLoader {
    private String readFile(final String fileName) throws IOException {
        final File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return new String(Files.readAllBytes(file.toPath()));
    }

    /**
     * This methos load the RSA private key from a PKCS#8 PEM file.
     *
     * @param pemFilename
     * @return
     * @throws Exception
     */
    private PrivateKey loadPemRsaPrivateKey(String pemFilename) throws Exception {

        String pemString = readFile(pemFilename);

        String privateKeyPEM = pemString
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END RSA PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return keyFactory.generatePrivate(keySpec);
    }

    public PrivateKey load(String file) throws Exception {
        return loadPemRsaPrivateKey(file);
    }
}
