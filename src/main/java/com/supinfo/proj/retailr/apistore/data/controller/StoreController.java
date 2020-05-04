package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Store;
import com.supinfo.proj.retailr.apistore.data.repository.StoreRepository;
import javassist.NotFoundException;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public Iterable<Store> getStores(){
        logger.info("GET on /stores");
        return this.storeRepository.findAll();
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable long id) {
        logger.info("GET on /stores/{id} with param : " + id);
        if (this.storeRepository.existsByStoreId(id)){
            return ResponseEntity.ok(storeRepository.findStoreByStoreId(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/stores/add")
    public String createStore(@Valid @RequestBody Store store, BindingResult result){
        logger.info("POST on /stores/add");
        if (result.hasErrors()){
            return "{\"Invalid format\":1}";
        } else {
            storeRepository.save(store);
            return "{\"Store successfully created\":1}";
        }
    }

    @PostMapping("/stores/update/{id}")
    public ResponseEntity<String> updateStore(@PathVariable long id, @Valid @RequestBody Store store, BindingResult result) throws NotFoundException{
        logger.info("POST on /stores/{id}");
        if (result.hasErrors()){
            return ResponseEntity.badRequest().build();
        } else {
            if (this.storeRepository.existsByStoreId(id)){
                storeRepository.save(store);
                return ResponseEntity.ok("Store updated");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping("/stores/delete/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable long id) throws NotFoundException{
        logger.info("POST on /stores/delete/{id}");
        if (this.storeRepository.existsByStoreId(id)){
            this.storeRepository.delete(this.storeRepository.findStoreByStoreId(id).get());
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok("Store Updated");
        }
    }
}
