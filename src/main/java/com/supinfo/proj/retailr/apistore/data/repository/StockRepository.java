package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {
    boolean existsByEan(String ean);
    Iterable<Stock> findByEan(String ean);
    Iterable<Stock> findByEanAndStoreTypeLike(String ean, String type);
    Iterable<Stock> findByEanAndStoreTypeNotLike(String ean, String type);
}
