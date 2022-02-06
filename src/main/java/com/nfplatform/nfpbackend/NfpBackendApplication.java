package com.nfplatform.nfpbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class NfpBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfpBackendApplication.class, args);
    }

}
