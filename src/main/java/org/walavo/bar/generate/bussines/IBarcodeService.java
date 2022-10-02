package org.walavo.bar.generate.bussines;

import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface IBarcodeService {

    Mono<Response> generateBarcode(TypeGenerator typeGenerator, String productName) throws Exception;

    Mono<byte[]> getBarcode(String uuid);

    Flux<BarcodeDocument> getAllBarcode();

}
