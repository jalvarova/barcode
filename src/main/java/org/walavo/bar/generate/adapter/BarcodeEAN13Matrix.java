package org.walavo.bar.generate.adapter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.factory.BarcodeCodeFactory;

import java.awt.image.BufferedImage;

@Service
public class BarcodeEAN13Matrix implements BarcodeCodeFactory<BufferedImage> {
    @Override
    public BufferedImage generate(String decodeValue) {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(decodeValue, BarcodeFormat.EAN_13, 300, 150);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
