package com.supinfo.proj.retailr.apistore.service;

import com.supinfo.proj.retailr.apistore.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private ProductRepository productRepository;

    public List<String> getDepartments(){
        return this.productRepository.findDepartments();
    }

    public List<String> getFamilies(){
        return this.productRepository.findFamilies();
    }

    public List<String> getSubfamilies(){
        return this.productRepository.findSubfamilies();
    }
}
