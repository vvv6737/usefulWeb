package com.usefulNVersatileWeb.usefulWeb.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
@Configuration
public class JasyptConfig {

//    @Value("${jasypt.encryptor.key}")
//    private String KEY;

    private static String key() throws IOException{
        String path = System.getProperty("user.dir");
        String key2 = "";
        FileReader reader = new FileReader(path + "/../../../Downloads/usefulWebJasyptKey.txt");
        int ch;
        while ((ch = reader.read()) != -1) {
            key2 = key2 + (char) ch;
        }
        return key2;
    }

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() throws IOException {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key());
        config.setPoolSize("1");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setStringOutputType("base64");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptor.setConfig(config);
        return encryptor;
    }

}
