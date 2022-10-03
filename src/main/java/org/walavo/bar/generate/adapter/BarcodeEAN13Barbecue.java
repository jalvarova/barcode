package org.walavo.bar.generate.adapter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.factory.BarcodeCodeFactory;

import java.awt.image.BufferedImage;

import static org.walavo.bar.generate.util.ConvertImage.BARCODE_TEXT_FONT;

@Service
public class BarcodeEAN13Barbecue implements BarcodeCodeFactory<BufferedImage> {

    @Override
    public BufferedImage generate(String decodeValue) {
        BufferedImage bufferedImage;
        try {
            Barcode barcode = BarcodeFactory.createEAN13(decodeValue);
            barcode.setFont(BARCODE_TEXT_FONT);
            bufferedImage = BarcodeImageHandler.getImage(barcode);
        } catch (OutputException | BarcodeException e) {
            throw new RuntimeException(e);
        }
        return bufferedImage;
    }
}
