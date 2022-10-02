package org.walavo.bar.generate.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ConvertImage {

    public static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    /**
     * convert BufferedImage to byte[].
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
     *  convert byte[] to BufferedImage
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
}
