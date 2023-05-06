package com.stav.server;

import com.stav.server.entities.CompanyEntity;
import com.stav.server.entities.UserEntity;
import com.stav.server.logic.CompaniesLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.sql.Date;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
