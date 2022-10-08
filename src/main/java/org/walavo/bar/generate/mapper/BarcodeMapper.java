package org.walavo.bar.generate.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import org.walavo.bar.generate.util.ConvertImage;

import java.time.LocalDateTime;

@Component
public final class BarcodeMapper {

    @Value("${short-link}")
    private String shortLink;

    private String getShortLink() {
        return this.shortLink;
    }

    public static BarcodeDocument builder(String uuid, TypeGenerator typeGenerator, String value, byte[] bytes) {
        return BarcodeDocument
                .builder()
                .uuid(uuid)
                .barcodeByte(bytes)
                .name(typeGenerator.name())
                .encoder(value)
                .date(LocalDateTime.now())
                .build();
    }

    public Response applyApi(byte[] bytes) {
        return Response
                .builder()
                .image(bytes)
                .build();
    }

    public Response applyApi(String keyGenerate) {
        return Response
                .builder()
                .shortLink(getShortLink() + "/" + keyGenerate)
                .build();
    }
}
