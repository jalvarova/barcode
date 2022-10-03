package org.walavo.bar.generate.adapter;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.factory.BarcodeCodeFactory;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Component
public class BarcodeAdapter {

    @Autowired
    private BarcodeEAN13Barbecue barcodeEAN13Barbecue;
    @Autowired
    private BarcodeEAN13Bitmap barcodeEAN13Bitmap;
    @Autowired
    private BarcodeEAN13Matrix barcodeEAN13Matrix;
    @Autowired
    private BarcodeQRMatrix barcodeQRMatrix;

    private static final Map<TypeGenerator, BarcodeCodeFactory<BufferedImage>> mapBarcode = new HashMap<>();

    @PostConstruct
    public void init() {
        mapBarcode.put(TypeGenerator.BARCODE, barcodeEAN13Barbecue);
        mapBarcode.put(TypeGenerator.QR, barcodeQRMatrix);
        mapBarcode.put(TypeGenerator.EAN13, barcodeEAN13Matrix);
        mapBarcode.put(TypeGenerator.BITMAP, barcodeEAN13Bitmap);
    }

    public static BarcodeCodeFactory<BufferedImage> getStrategy(TypeGenerator typeGenerator) {
        BarcodeCodeFactory<BufferedImage> barcodeCodeFactory = mapBarcode.get(typeGenerator);
        Assert.assertNotNull(String.format("Not exists strategy for typeGenerator : %s", typeGenerator), barcodeCodeFactory);
        return barcodeCodeFactory;
    }
}
