package com.msp.givn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.msp.givn.*")
public class GiVnApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiVnApplication.class, args);
    }

}

