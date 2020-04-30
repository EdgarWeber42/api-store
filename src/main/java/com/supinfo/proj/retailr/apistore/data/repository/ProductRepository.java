package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findProductByEan(String string);
    Iterable<Product> findProductByDepartment(String string);
    Iterable<Product> findProductByFamily(String string);
    Iterable<Product> findProductBySubfamily(String string);
}
