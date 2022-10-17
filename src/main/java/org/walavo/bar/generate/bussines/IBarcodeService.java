package org.walavo.bar.generate.bussines;

import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.StaticCache;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IBarcodeService {

    Mono<Response> generateBarcode(TypeGenerator typeGenerator, String productName);

    Mono<byte[]> getBarcode(String uuid);

    Flux<BarcodeDocument> getAllBarcode();

    Mono<Response> shortLink(String uuid);
    Mono<Map<String, String>> redirectLink(String link);

    Mono<Response> shortLinkStatic(StaticCache body);

}
