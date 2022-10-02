package org.walavo.bar.generate.bussines;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.apache.commons.codec.binary.Base64;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;
import org.walavo.bar.generate.dto.Response;
import org.walavo.bar.generate.dto.TypeGenerator;
import org.walavo.bar.generate.model.document.BarcodeDocument;
import org.walavo.bar.generate.model.repository.BarcodeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BarcodeService implements IBarcodeService {

    private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    private final BarcodeRepository barcodeRepository;

    @Override
    public Mono<Response> generateBarcode(TypeGenerator typeGenerator, String productName) throws Exception {
        BufferedImage bufferedImage;
        if (typeGenerator == TypeGenerator.BARCODE) {
            Barcode barcode = BarcodeFactory.createEAN13(productName);
            barcode.setFont(BARCODE_TEXT_FONT);
            bufferedImage = BarcodeImageHandler.getImage(barcode);
        } else if (typeGenerator == TypeGenerator.QR) {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(productName, BarcodeFormat.QR_CODE, 250, 250);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } else if (typeGenerator == TypeGenerator.EAN13) {
            EAN13Writer barcodeWriter = new EAN13Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(productName, BarcodeFormat.EAN_13, 300, 150);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } else {
            EAN13Bean barcodeGenerator = new EAN13Bean();
            BitmapCanvasProvider canvas =
                    new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            barcodeGenerator.generateBarcode(canvas, productName);
            bufferedImage = canvas.getBufferedImage();
        }

        String uuid = UUID.randomUUID().toString();
        byte[] bytes = toByteArray(bufferedImage, "png");
        //encode the byte array for display purpose only, optional
        String bytesBase64 = Base64.encodeBase64String(bytes);

        System.out.println(bytesBase64);

        // decode byte[] from the encoded string
        byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);
        BufferedImage newBi = toBufferedImage(bytesFromDecode);

        BarcodeDocument document = BarcodeDocument
                .builder()
                .uuid(uuid)
                .barcodeByte(bytesFromDecode)
                .name(typeGenerator.name())
                .encoder(productName)
                .date(LocalDateTime.now())
                .build();

        barcodeRepository.save(document).subscribe();

        // save it somewhere
        ImageIO.write(newBi, "png", new File("img/bar".concat("-").concat(uuid).concat(".png")));
        Response response = Response.builder().image(bytes).build();
        return Mono.just(response);
    }

    @Override
    public Mono<byte[]> getBarcode(String uuid) {

        return barcodeRepository.findByUuid(uuid)
                .map(BarcodeDocument::getBarcodeByte);
    }

    @Override
    public Flux<BarcodeDocument> getAllBarcode() {
        return barcodeRepository.findAll();
    }

    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        return baos.toByteArray();

    }

    // convert byte[] to BufferedImage
    public BufferedImage toBufferedImage(byte[] bytes) {

        InputStream is = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
