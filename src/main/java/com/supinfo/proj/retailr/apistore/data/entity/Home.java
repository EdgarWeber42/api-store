package com.supinfo.proj.retailr.apistore.data.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public class Home {
    private String title;
    private List<String> supportedMethods;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSupportedMethods() {
        return supportedMethods;
    }

    public void setSupportedMethods(List<String> supportedMethods) {
        this.supportedMethods = supportedMethods;
    }

    @Autowired
    public Home(String title, List<String> supportedMethods) {
        this.title = title;
        this.supportedMethods = supportedMethods;
    }
}
