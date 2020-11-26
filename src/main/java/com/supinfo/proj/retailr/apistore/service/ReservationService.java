package com.supinfo.proj.retailr.apistore.service;


import com.supinfo.proj.retailr.apistore.data.repository.ItemRepository;
import com.supinfo.proj.retailr.apistore.data.repository.ProductRepository;
import com.supinfo.proj.retailr.apistore.data.repository.StoreRepository;
import com.supinfo.proj.retailr.apistore.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void makeReservation(String token, String ean, long storeId) throws Exception {
        if (!this.productRepository.existsByEan(ean)) {
            throw new Exception("Product with ean " + ean + " doesn't exist");
        }
        if (!this.storeRepository.existsByStoreId(storeId)){
            throw new Exception("Store with id " + storeId + " doesn't exist");
        }
    }

}
