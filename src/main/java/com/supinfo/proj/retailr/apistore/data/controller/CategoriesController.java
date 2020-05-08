package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.service.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartment() {
        logger.info("GET on /departments");
        return ResponseEntity.ok(this.categoriesService.getDepartments());
    }

    @GetMapping("/families")
    public ResponseEntity<?> getFamilies() {
        logger.info("GET on /families");
        return ResponseEntity.ok(this.categoriesService.getFamilies());
    }

    @GetMapping("/subfamilies")
    public ResponseEntity<?> getSubfamilies() {
        logger.info("GET on /subfamilies");
        return ResponseEntity.ok(this.categoriesService.getSubfamilies());
    }
}
