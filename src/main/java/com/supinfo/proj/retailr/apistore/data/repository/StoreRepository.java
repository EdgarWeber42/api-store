package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreRepository extends CrudRepository<Store, Long> {
    Optional<Store> findStoreByStoreId(long id);
    boolean existsByStoreId(long Id);
}
