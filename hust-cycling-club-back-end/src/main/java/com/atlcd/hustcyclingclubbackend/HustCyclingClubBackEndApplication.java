package com.atlcd.hustcyclingclubbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.atlcd.hustcyclingclubbackend.dao.mapper")
public class HustCyclingClubBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(HustCyclingClubBackEndApplication.class, args);
    }

}
