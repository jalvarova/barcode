# Description API Barcode :lock:

API generate the Barcode of the companies of the different channels

## Stack Technology :call_me_hand:

    * String Boot + Webflux
    * Programming Reactive
    * Barcode and QR Generate
    * Redis
    * Mongo
    * Pattern Adapter
    * Pattern Builder

## Mongo install docker compose :open_file_folder:

```bash
$ cd mongo
$ docker-compose up -d
```

## Redis install docker compose :open_file_folder:

```bash
$ cd redis
$ docker-compose up -d
```

## Build Project Spring Boot :+1:

```bash
./mvnw spring-boot:run
```

## Postman Collection 

[Ruta Collection](BARCODES.postman_collection.json) :open_file_folder:

### References

[Generate QR](https://medium.com/techwasti/qr-code-generator-as-rest-api-using-spring-boot-7f1cc9194073)

[Barcodes-qr](https://www.baeldung.com/java-generating-barcodes-qr-codes)

[Mongo Reactive](https://www.baeldung.com/spring-data-mongodb-reactive)

[Database R2bdc](https://codetinkering.com/r2dbc-reactive-database-example-in-spring/)

[Minikube](https://minikube.sigs.k8s.io/docs/start/)