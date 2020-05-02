## Retailr API-Store

The store API from the Retailr group for Supinfo 4PROJ 2019/2020  
Goal is to serve the data for the stores (Product referential, events, stocks...)  
And to apply the businness logic

### Requirements

- Java 11 installed and $JAVA_HOME env variable
- Maven v 3 at least
- [Shop-DB](https://gitlab.saaas.io/4proj/shop-db) running on localhost:5432


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

Via Docker
```bash
docker run --rm --name api-store -p 8080:8080 -e API_STORE_PG_URL={shop db url or ip} rg.saaas.io/4pjt/api-store
```

### How to build ?

via [Jib](https://github.com/GoogleContainerTools/jib) (recommended)

```bash
./mvnw com.google.cloud.tools:jib-maven-plugin:build -Dimage={Docker hub username}/api-store
```

via Maven

```bash
mvn clean package
```

### Staging env

api store is available at :  
https://api-store.lemoy-remlin.ml/


### Supported URL patterns

##### Products

`GET  localhost:8080/products`  
Retrieve all products

`GET localhost:8080/products/{ean}`  
Retrieve products by EAN

`GET localhost:8080/products/department/{department}`
Retrieve all the products from a department (for exemple : outillage)

`GET localhost:8080/products/{family}`
Retrieve all the products from a family (for exemple : outillage-a-main)

`GET localhost:8080/products/{subfamily}`
Retrieve all the products from a subfamily (for exemple : cle-et-douille)

`POST localhost:8080/products/update/{ean}`
plus a valid JSON product :
```json
{
  "productId": 48,
  "ean": "3663602814917",
  "name": "Clé à molette Magnusson 6, 8 et 10 - 3 pièces",
  "price": 24.9,
  "department": "outillage",
  "family": "outillage-a-main",
  "subfamily": "cle-et-douille"
}
```
Edit a product, the ean must be valid

`POST localhost:8080/products/add/`
plus a valid JSON products
Create a product

`POST localhost:8080/products/delete/{ean}`
Delete a product, the ean must exist

