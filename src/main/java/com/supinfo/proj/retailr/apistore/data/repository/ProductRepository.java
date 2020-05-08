package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import javax.persistence.NamedQueries;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findProductByEan(String string);
    Iterable<Product> findProductByDepartment(String string);
    Iterable<Product> findProductByFamily(String string);
    Iterable<Product> findProductBySubfamily(String string);


    // Following are for the Categories Service /!\

    @Query(value = "SELECT DISTINCT department from products", nativeQuery = true)
    List<String> findDepartments();

    @Query(value = "SELECT DISTINCT family from products", nativeQuery = true)
    List<String> findFamilies();

    @Query(value = "SELECT DISTINCT subfamily from products", nativeQuery = true)
    List<String> findSubfamilies();
}
