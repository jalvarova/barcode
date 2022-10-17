# Description API Barcode

API generate the Barcode of the companies of the different channels

## Stack Technology

    * String Boot + Webflux
    * Programming Reactive
    * Barcode and QR Generate
    * Redis
    * Mongo
    * Pattern Adapter
    * Pattern Builder

## Mongo install docker compose

```bash
$ cd mongo :open_file_folder:
$ docker-compose up -d
```

## Redis install docker compose

```bash
$ cd redis :open_file_folder:
$ docker-compose up -d
```

## Build Project Spring Boot

```bash
./mvnw spring-boot:run
```

## Postman Collection 

[Ruta Collection](BARCODES.postman_collection.json) :bookmark:

### References

[Generate QR](https://medium.com/techwasti/qr-code-generator-as-rest-api-using-spring-boot-7f1cc9194073)

[Barcodes-qr](https://www.baeldung.com/java-generating-barcodes-qr-codes)

[Mongo Reactive](https://www.baeldung.com/spring-data-mongodb-reactive)

[Database R2bdc](https://codetinkering.com/r2dbc-reactive-database-example-in-spring/)

[Minikube](https://minikube.sigs.k8s.io/docs/start/)