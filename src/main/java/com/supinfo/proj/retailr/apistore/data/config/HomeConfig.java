package com.supinfo.proj.retailr.apistore.data.config;

import com.supinfo.proj.retailr.apistore.data.entity.Home;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class HomeConfig {
    @Bean
    public Home getHome(){
        return new Home(title(),supportedMethods());
    }

    @Bean
    public String title(){
        return "API Store";
    }

    @Bean
    public List<String> supportedMethods(){
        return Arrays.asList(
                "/products",
                "/items",
                "/stores",
                "/stocks"
        );
    }
}
