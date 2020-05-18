package com.supinfo.proj.retailr.apistore.service;

import com.sun.xml.bind.v2.TODO;
import com.supinfo.proj.retailr.apistore.data.entity.Product;
import com.supinfo.proj.retailr.apistore.data.repository.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;

@Service
public class ProductSearchService {

    @Autowired
    private URLDecoder urlDecoder;

    @Autowired
    private ProductSearch productSearch;

    //TODO finish service => dispatch if multiple words search ....
    public Iterable<Product> search(String fts){
        return null;
    }

}
