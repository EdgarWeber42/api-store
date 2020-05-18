package com.supinfo.proj.retailr.apistore.config;

import com.supinfo.proj.retailr.apistore.data.entity.Product;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.URLDecoder;

@Configuration
public class SearchServiceConfig {
    @Bean
    public URLDecoder getUrlDecoder(){
        return new URLDecoder();
    }
}
