package com.supinfo.proj.retailr.apistore.data.conSALEtroller;

import com.supinfo.proj.retailr.apistore.data.entity.Stock;
import com.supinfo.proj.retailr.apistore.data.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/stocks")
    public ResponseEntity<Iterable<Stock>> getStocks(){
        logger.info("GET on /stocks");
        return ResponseEntity.ok().body(this.stockRepository.findAll());
    }

    @GetMapping("/stocks/{ean}")
    public ResponseEntity<?> getStockByEan(@PathVariable String ean){
        if (this.stockRepository.existsById(ean)){
            return ResponseEntity.ok().body(this.stockRepository.findByEan(ean).get());
        } else {
            return ResponseEntity.badRequest().body("ean" + ean + "does not exist");
        }
    }
}
