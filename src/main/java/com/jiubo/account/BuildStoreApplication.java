package com.jiubo.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jiubo.account.dao")
public class BuildStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildStoreApplication.class, args);
    }

}
