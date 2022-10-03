package org.walavo.bar.generate.adapter;

import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.factory.BarcodeCodeFactory;

import java.awt.image.BufferedImage;

@Service
public class BarcodeEAN13Bitmap implements BarcodeCodeFactory<BufferedImage> {

    @Override
    public BufferedImage generate(String decodeValue) {
        EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        barcodeGenerator.generateBarcode(canvas, decodeValue);
        return canvas.getBufferedImage();
    }
}
