## Retailr API-Store

The store API from the Retailr group for Supinfo 4PROJ 2019/2020  
Goal is to serve the data for the stores (Product referential, events, stocks...)

### Requirements

- Java 11 installed and $JAVA_HOME env variable
- Maven v 3 at least
- [Shop-DB](https://gitlab.saaas.io/4proj/shop-db) running on localhost:5432 (will parameterize db url)

*Docker file in progress 😎*

### How to run ?

Once requirements are met, cd at the root of the project and :

On Unix systems
```bash
./mvnw spring-boot:run
```

On Windows
```bash
./mvnw.cmd spring-boot:run
```

*once again, Docker file incoming*

### Supported URL patterns

At the moment :

`GET  localhost:8080/products`  
Retrieve all products

`GET localhost:8080/products?ean={ean}`  
Retrieve products by EAN