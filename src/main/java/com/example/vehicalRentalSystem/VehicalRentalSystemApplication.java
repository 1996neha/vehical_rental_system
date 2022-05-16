package com.example.vehicalRentalSystem;

import com.example.vehicalRentalSystem.executor.AppExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VehicalRentalSystemApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VehicalRentalSystemApplication.class, args);
        String filePath = args[0];
        AppExecutor appExecutor = context.getBean(AppExecutor.class);
        appExecutor.readFileCmd(filePath);
    }

}
