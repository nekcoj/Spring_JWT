package com.whistleblower.app.cryptography;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class BasicTextEncryptor {

    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public BasicTextEncryptor() {
        this.encryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    public StandardPBEStringEncryptor getEncryptor() {
        return encryptor;
    }
}
