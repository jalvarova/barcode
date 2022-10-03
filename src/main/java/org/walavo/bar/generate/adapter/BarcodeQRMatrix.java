package org.walavo.bar.generate.adapter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.factory.BarcodeCodeFactory;

import java.awt.image.BufferedImage;

@Service
public class BarcodeQRMatrix implements BarcodeCodeFactory<BufferedImage> {
    @Override
    public BufferedImage generate(String decodeValue) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(decodeValue, BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
