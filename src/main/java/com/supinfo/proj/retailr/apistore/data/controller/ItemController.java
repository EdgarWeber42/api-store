package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Item;
import com.supinfo.proj.retailr.apistore.data.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Item> getItemByEPC(@PathVariable String epc){
        logger.info("GET on /items/{epc} with param : " + epc);
        if (this.itemRepository.existsByEpc(epc)){
            return ResponseEntity.ok(this.itemRepository.findItemByEpc(epc).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/items/ean/{ean}")
    public ResponseEntity<?> getItemByEan(@PathVariable String ean){
        logger.info("GET on /items/ean/{ean} with param : " + ean);
        if (this.itemRepository.existsByProductEan(ean)){
            return ResponseEntity.ok(this.itemRepository.findByProductEan(ean));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/items/update/{epc}")
    public ResponseEntity<String> updateItem(@PathVariable String epc, @RequestBody(required = false) @Valid Item item, @RequestParam(required = false) String state){
        if (this.itemRepository.existsByEpc(epc)){
            if (state != null){
                if (state.equals("SALESAREA") || state.equals("SOLD") || state.equals("STOCK")){
                    logger.info("POST on /items/update/{epc} with state : " + state);
                    Item thisEditedItem = this.itemRepository.findItemByEpc(epc).get();
                    thisEditedItem.setState(state);
                    this.itemRepository.save(thisEditedItem);
                    return ResponseEntity.ok().body("Item state successfully updated");
                } else {
                    return ResponseEntity.badRequest().body("State must be within : 'STOCK', 'SALESAREA', 'SOLD'");
                }
            } else {
                this.itemRepository.save(item);
                return ResponseEntity.ok().body("Item successfully updated");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found");
        }
    }

    @PostMapping("/items/add")
    public ResponseEntity<String> createItem(@RequestBody @Valid Item item, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("wrong format");
        } else if (!this.itemRepository.existsByEpc(item.getEpc())){
            return ResponseEntity.badRequest().body("Item already exists");
        } else {
            this.itemRepository.save(item);
            return ResponseEntity.ok("Product created");
        }
    }

    @PostMapping("/items/delete/{epc}")
    public ResponseEntity<String> deleteItem(@PathVariable String epc){
        logger.info("POST on /items/delete/{epc} with param : " + epc);
        if (this.itemRepository.existsByEpc(epc)){
            this.itemRepository.delete(this.itemRepository.findItemByEpc(epc).get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
