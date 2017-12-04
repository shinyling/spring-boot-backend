package com.shiny.backend.trasaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shiny
 **/
@SpringBootApplication
@ComponentScan(basePackages={"com.shiny.backend"})
public class TransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class,args);
    }
}
