package org.walavo.bar.generate.bussines;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.adapter.BarcodeAdapter;
import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import static org.walavo.bar.generate.mapper.BarcodeMapper.builder;

import org.walavo.bar.generate.mapper.BarcodeMapper;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import org.walavo.bar.generate.model.repository.BarcodeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;
import java.util.UUID;

import static org.walavo.bar.generate.util.ConvertImage.writeImage;

@Service
@RequiredArgsConstructor
public class BarcodeService implements IBarcodeService {

    private final BarcodeRepository barcodeRepository;

    @Override
    public Mono<Response> generateBarcode(TypeGenerator typeGenerator, String value) {
        String uuid = UUID.randomUUID().toString();
        BufferedImage bufferedImage = BarcodeAdapter.getStrategy(typeGenerator).generate(value);
        byte[] bytesDecode = writeImage(bufferedImage, uuid);
        return barcodeRepository.save(builder(uuid, typeGenerator, value, bytesDecode))
                .map(BarcodeDocument::getBarcodeByte)
                .map(BarcodeMapper::applyApi);
    }

    @Override
    public Mono<byte[]> getBarcode(String uuid) {
        return barcodeRepository.findByUuid(uuid).map(BarcodeDocument::getBarcodeByte);
    }

    @Override
    public Flux<BarcodeDocument> getAllBarcode() {
        return barcodeRepository.findAll();
    }
}
