package org.walavo.bar.generate.bussines;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.adapter.BarcodeAdapter;
import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.StaticCache;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.mapper.BarcodeMapper;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import org.walavo.bar.generate.model.repository.BarcodeRepository;
import org.walavo.bar.generate.model.repository.CacheRedisRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.UUID;

import static org.walavo.bar.generate.mapper.BarcodeMapper.builder;
import static org.walavo.bar.generate.util.ConvertImage.writeImage;

@Service
@RequiredArgsConstructor
public class BarcodeService implements IBarcodeService {

    private final BarcodeRepository barcodeRepository;
    private final BarcodeMapper barcodeMapper;

    private final CacheRedisRepository cacheRedisRepository;

    @Override
    public Mono<Response> generateBarcode(TypeGenerator typeGenerator, String value) {
        String uuid = UUID.randomUUID().toString();
        BufferedImage bufferedImage = BarcodeAdapter.getStrategy(typeGenerator).generate(value);
        byte[] bytesDecode = writeImage(bufferedImage, uuid);

        return barcodeRepository.save(builder(uuid, typeGenerator, value, bytesDecode))
                .map(BarcodeDocument::getBarcodeByte)
                .map(barcodeMapper::applyApi);
    }

    @Override
    public Mono<byte[]> getBarcode(String uuid) {
        return barcodeRepository
                .findByUuid(uuid)
                .map(BarcodeDocument::getBarcodeByte);
    }

    @Override
    public Flux<BarcodeDocument> getAllBarcode() {
        return barcodeRepository.findAll();
    }

    @Override
    public Mono<Response> shortLink(String value) {
        return cacheRedisRepository
                .defaultTtl()
                .registerCache(value)
                .map(barcodeMapper::applyApi);
    }

    @Override
    public Mono<Map<String, String>> redirectLink(String link) {
        String[] arrays = link.split("/");
        String keyCache = arrays[arrays.length - 1];
        return cacheRedisRepository
                .getCache(keyCache)
                .map(s -> Map.of("url", s));
    }

    @Override
    public Mono<Response> shortLinkStatic(StaticCache body) {
        return cacheRedisRepository
                .ttlNotExpire()
                .saveCache(body.getKey(), body.getLink())
                .map(barcodeMapper::applyApi);
    }
}
