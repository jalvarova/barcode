package org.walavo.bar.generate.mapper;

import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;

import java.time.LocalDateTime;

public final class BarcodeMapper {
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
    public static Response applyApi(byte[] bytes) {
        return Response.builder().image(bytes).build();
    }
}
