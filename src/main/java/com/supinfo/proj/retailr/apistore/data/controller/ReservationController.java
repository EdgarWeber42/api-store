package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<?> makeReservation(@RequestHeader("Authorization") String auth,
                                             @RequestParam("store") long storeId,
                                             @RequestParam("product") String ean){
        logger.info("POST on /reservation, storeId : " + storeId + ", ean : " + ean + ", auth : " + auth);
        String token = auth.substring(7);
        try {
            this.reservationService.makeReservation(token, ean, storeId);
            return ResponseEntity.ok().body(new Response("item successfully reserved"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("couldn't make reservation" + e.getCause());
        }
    }

}
