package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<?> getItems(){
        return ResponseEntity.ok(this.itemRepository.findAll());
    }

    @GetMapping("/items/{epc}")
    public ResponseEntity<?> getItemByEPC(@PathVariable String epc){
        logger.info("GET on /items/{epc} with param : " + epc);
        if (this.itemRepository.existsByEpc(epc)){
            return ResponseEntity.ok(this.itemRepository.findItemByEpc(epc).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
