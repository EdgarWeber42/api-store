package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import com.supinfo.proj.retailr.apistore.data.repository.ProductRepository;
import com.supinfo.proj.retailr.apistore.data.repository.ProductSearch;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSearch productSearch;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String fts){
        if (fts != null){
            logger.info("FTS on /products" + fts);
            return ResponseEntity.ok().body(this.productSearch.searchByName(fts));
        }
            logger.info("/GET on /products with no ean");
            return ResponseEntity.ok().body(this.productRepository.findAll());
    }

    @GetMapping("/products/{ean}")
    public Product getProductByEan(@PathVariable String ean) throws NotFoundException{
        logger.info("GET on /products/{ean} with param : " + ean);
        return this.productRepository.findProductByEan(ean)
                .orElseThrow(() -> new NotFoundException("product with ean " + ean + " does not exist"));
    }

    @GetMapping("/products/department/{department}")
    public Iterable<Product> getProductByDepartment(@PathVariable String department){
        logger.info("GET on /products/department/{department} with param : " + department);
        return this.productRepository.findProductByDepartment(department);
    }

    @GetMapping("/products/family/{family}")
    public Iterable<Product> getProductByFamily(@PathVariable String family){
        logger.info("GET on /products/family/{family} with param : " + family);
        return this.productRepository.findProductByFamily(family);
    }

    @GetMapping("/products/subfamily/{subfamily}")
    public Iterable<Product> getProductBySubfamily(@PathVariable String subfamily){
        logger.info("GET on /products/subfamily/{subfamily} with param : " + subfamily);
        return this.productRepository.findProductBySubfamily(subfamily);
    }

    @PostMapping("/products/update/{ean}")
    public String editProduct(@PathVariable String ean, @Valid @RequestBody Product product, BindingResult result) throws NotFoundException{
        logger.info("POST on /products/update/{ean} with product of ean : " + product.getEan());
            this.productRepository.findProductByEan(ean)
            .orElseThrow(() -> new NotFoundException("Product with ean " + ean + " does not exist"));
            productRepository.save(product);
            return "{\"Product successfully edited\":1}";
    }

    @PostMapping("/products/add")
    public String createProduct(@Valid @RequestBody Product product, BindingResult result){
        logger.info("POST on /products/add with product of param = " + product.getEan());
        if (result.hasErrors()){
            return "{\"Invalid format\":1}";
        } else {
            productRepository.save(product);
            return "{\"Product successfully created\":1}";
        }
    }
}
