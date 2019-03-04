package com.msp.givn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.msp.givn.*")
public class GiVnApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GiVnApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder createSpringApplicationBuilder() {
        return super.createSpringApplicationBuilder();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GiVnApplication.class);
    }
}

