package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import com.supinfo.proj.retailr.apistore.data.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Iterable<Product> getProductsByEan(@RequestParam(name = "ean", required = false) String ean){
        if (ean == null){
            logger.info("/GET on /products with no ean");
            return this.productRepository.findAll();
        } else {
            logger.info("GET on /products with param : ean = " + ean);
            return this.productRepository.findProductByEan(ean);
        }
    }

}
