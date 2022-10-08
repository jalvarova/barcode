package org.walavo.bar.generate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.walavo.bar.generate.bussines.IBarcodeService;
import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
public class BarcodeController {

    private final IBarcodeService barcodeService;

    @GetMapping(value = "/barcodes/ean13",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response> getGenerateBarcode(@RequestParam("productName") String productName) {
        return barcodeService.generateBarcode(TypeGenerator.BARCODE, productName);
    }

    @GetMapping(value = "/barcodes/bitmap",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response> getGenerateBarcodeBitmap(@RequestParam("productName") String productName) {
        return barcodeService.generateBarcode(TypeGenerator.BITMAP, productName);
    }

    @GetMapping(value = "/barcodes/qr",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response> getGenerateQR(@RequestParam("url") String url) {
        return barcodeService.generateBarcode(TypeGenerator.QR, url);
    }

    @GetMapping(value = "/barcodes/matrix/ean",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response> getGenerateEAN13(@RequestParam("productName") String productName) {
        return barcodeService.generateBarcode(TypeGenerator.EAN13, productName);
    }


    @GetMapping(value = "/barcodes/image/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<byte[]> getBarcode(@PathVariable("uuid") String uuid) {
        return barcodeService.getBarcode(uuid);
    }


    @GetMapping(value = "/barcodes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BarcodeDocument> getBarcodes() {
        return barcodeService.getAllBarcode();
    }

    @GetMapping(value = "/barcodes/short",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response> shortLink(@RequestParam("url") String url) {
        return barcodeService.shortLink(url);
    }

    @GetMapping(value = "/barcodes/redirect/{link}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, String>> redirectLink(@PathVariable("link") String link) {
        return barcodeService.redirectLink(link);
    }
}
