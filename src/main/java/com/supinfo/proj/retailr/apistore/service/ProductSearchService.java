package com.supinfo.proj.retailr.apistore.service;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import com.supinfo.proj.retailr.apistore.data.repository.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
public class ProductSearchService {

    @Autowired
    private ProductSearch productSearch;

    private boolean hasMultipleWords(String query){
        return query.contains(" ");
    }

    private String decodeValue(String query){
        try {
            return URLDecoder.decode(query, StandardCharsets.UTF_8);
        } catch (Exception e){
            throw new RuntimeException("couldn't decode query : " + e.getCause());
        }
    }

    public Iterable<Product> search(String fts){
        if (hasMultipleWords(fts)){
            return this.productSearch.searchByNamePhrase(fts);
        } else {
            return this.productSearch.searchByName(fts);
        }
    }

}
