package org.walavo.bar.generate.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import reactor.core.publisher.Mono;

@Repository
public interface BarcodeRepository extends ReactiveMongoRepository<BarcodeDocument, String> {
    Mono<BarcodeDocument> findByUuid(String uuid);
}
