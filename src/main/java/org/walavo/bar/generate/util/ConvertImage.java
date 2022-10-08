package org.walavo.bar.generate.util;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class ConvertImage {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    /**
     * convert BufferedImage to byte[].
     *
     * @param bi
     * @param format
     * @return byte[]
     * @throws IOException
     */
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        return baos.toByteArray();

    }

    /**
     * convert byte[] to BufferedImage
     *
     * @param bytes
     * @return BufferedImage
     */
    public static BufferedImage toBufferedImage(byte[] bytes) {

        InputStream is = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Write Image
     * @param bufferedImage
     * @param uuid
     * @return byte[]
     */
    public static byte[] writeImage(BufferedImage bufferedImage, String uuid) {
        byte[] bytes;
        byte[] bytesFromDecode;
        try {
            bytes = ConvertImage.toByteArray(bufferedImage, "png");
            //encode the byte array for display purpose only, optional
            String bytesBase64 = Base64.encodeBase64String(bytes);
            // decode byte[] from the encoded string
            bytesFromDecode = Base64.decodeBase64(bytesBase64);

            BufferedImage newBi = ConvertImage.toBufferedImage(bytesFromDecode);
            ImageIO.write(newBi, "png", new File("img/bar".concat("-").concat(uuid).concat(".png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytesFromDecode;
    }

    public static String decodeToString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
