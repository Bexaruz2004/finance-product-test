package com.example.financeproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FinanceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceProductApplication.class, args);
    }

}
