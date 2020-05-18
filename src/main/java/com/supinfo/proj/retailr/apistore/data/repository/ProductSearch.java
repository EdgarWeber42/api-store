package com.supinfo.proj.retailr.apistore.data.repository;


import com.supinfo.proj.retailr.apistore.data.entity.Product;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductSearch {

    private static Logger logger = LoggerFactory.getLogger(ProductSearch.class);

    @PersistenceContext
    private EntityManager entityManager;

    private FullTextEntityManager getFTEntityManager(){
        return Search.getFullTextEntityManager(entityManager);
    }

    private QueryBuilder getQueryBuilder(FullTextEntityManager fullTextEntityManager){
        return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
    }

    public Iterable<Product> searchByName(String text){
        FullTextEntityManager fullTextEntityManager = this.getFTEntityManager();
        QueryBuilder queryBuilder = this.getQueryBuilder(fullTextEntityManager);
        org.apache.lucene.search.Query query =
                queryBuilder
                .keyword()
                .onField("name")
                .matching(text)
                .createQuery();
        FullTextQuery jqaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
        @SuppressWarnings("unchecked")
        Iterable<Product> results = jqaQuery.getResultList();
        return results;
    }

    //TODO replace void => finish phrase query
    public Iterable<Product> searchByNamePhrase(String text){
        FullTextEntityManager fullTextEntityManager = this.getFTEntityManager();
        QueryBuilder queryBuilder = this.getQueryBuilder(fullTextEntityManager);

        org.apache.lucene.search.PhraseQuery query = (PhraseQuery) queryBuilder
                .phrase()
                .withSlop(1)
                .onField("name")
                .sentence(text)
                .createQuery();

        FullTextQuery jqaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
        @SuppressWarnings("unchecked")
        Iterable<Product> results = jqaQuery.getResultList();

        return results;
    }

}
