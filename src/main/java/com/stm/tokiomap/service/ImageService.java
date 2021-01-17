package com.stm.tokiomap.service;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class ImageService {

   private static final int X_1_NATURAL = 13961;
   private static final int Y_1_NATURAL = 3582;

   private static final int X_2_NATURAL = 13992;
   private static final int Y_2_NATURAL = 3554;

    public static final String TOKYO_PNG = "/tokyo.png";
    public static final String TOKYO_1_PNG = "tokyo1.png";
    public static final String TOKYO_2_PNG = "tokyo2.png";
    private final ClassLoader loader = getClass().getClassLoader();

    public static BufferedImage cropImageWithNaturalCoordinates(byte[] image, float y1, float x1, float y2, float x2) throws IOException {
        BufferedImage originalImage = getBufferedImage(image);

        int xMaxNat = Math.min(Integer.max((int) (x2*100), (int)(x1*100)), X_2_NATURAL)- X_1_NATURAL;
        int xMinNat = Math.max(Integer.min((int)(x1*100),(int)( x2*100)), X_1_NATURAL)- X_1_NATURAL;
        int yMaxNat = Math.min(Integer.max((int)(y2*100),(int)( y1*100)), Y_1_NATURAL)- Y_2_NATURAL;
        int yMinNat = Math.max(Integer.min((int)(y1*100),(int) (y2*100)), Y_2_NATURAL)- Y_2_NATURAL;

        int xMax = xMaxNat*800/(X_2_NATURAL - X_1_NATURAL); // 400
        int xMin = xMinNat*800/(X_2_NATURAL - X_1_NATURAL); // 0

        int yMax = 800-yMaxNat*800/(Y_1_NATURAL - Y_2_NATURAL); // 400
        int yMin = 800-yMinNat*800/(Y_1_NATURAL - Y_2_NATURAL); // 0
        int tmp = yMax;
        yMax = yMin;
        yMin = tmp;

            BufferedImage bufferedImage = originalImage.getSubimage(xMin, yMin, (xMax-xMin), (yMax-yMin));
            return Thumbnails.of(bufferedImage).size(800, 800).asBufferedImage();
    }

    private static BufferedImage getBufferedImage(final byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        return ImageIO.read(in);
    }

    public static BufferedImage cropMinimapNaturalCoordinates(byte[] image, float y1, float x1, float y2, float x2) throws IOException {
        BufferedImage originalImage = getBufferedImage(image);

        int xMaxNat = Math.min(Integer.max((int) (x2*100), (int)(x1*100)), X_2_NATURAL)- X_1_NATURAL;
        int xMinNat = Math.max(Integer.min((int)(x1*100),(int)( x2*100)), X_1_NATURAL)- X_1_NATURAL;
        int yMaxNat = Math.min(Integer.max((int)(y2*100),(int)( y1*100)), Y_1_NATURAL)- Y_2_NATURAL;
        int yMinNat = Math.max(Integer.min((int)(y1*100),(int) (y2*100)), Y_2_NATURAL)- Y_2_NATURAL;

        int xMax = xMaxNat*800/(X_2_NATURAL - X_1_NATURAL);
        int xMin = xMinNat*800/(X_2_NATURAL - X_1_NATURAL);

        int yMax = 800-yMaxNat*800/(Y_1_NATURAL - Y_2_NATURAL);
        int yMin = 800-yMinNat*800/(Y_1_NATURAL - Y_2_NATURAL);
        int tmp = yMax;
        yMax = yMin;
        yMin = tmp;

        Graphics2D g2d = originalImage.createGraphics();
        g2d.setColor(Color.BLACK);
        float thickness = 5f;

        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(xMin, yMin, xMax, yMax);
        g2d.dispose();
        return originalImage;
    }


    public static BufferedImage cropImage(byte[] image, int y1, int x1, int y2, int x2) throws IOException {
        BufferedImage originalImage = getBufferedImage(image);

        int xMax = Math.min(Integer.max(x2, x1), 500);
        int xMin = Math.max(Integer.min(x1, x2), 0);
        int weight = xMax - xMin;
        int yMax = Math.min(Integer.max(y2, y1), 500);
        int yMin = Math.max(Integer.min(y1, y2), 0);
        int height = yMax - yMin;
        int x = weight / 2 + xMin;
        int y = height / 2 + yMin;
        return originalImage.getSubimage(x, y, weight, height);
    }

    public byte[] getModifiedImage(float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(TOKYO_PNG);

        BufferedImage image = cropImageWithNaturalCoordinates(IOUtils.toByteArray(in), y1, x1, y2, x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile() + TOKYO_1_PNG);
        ImageIO.write(image, "png", pathFile);

        InputStream out = loader.getResourceAsStream(TOKYO_1_PNG);

        return IOUtils.toByteArray(Objects.requireNonNull(out));
    }

    public byte[] getMinimap(float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(TOKYO_PNG);

        BufferedImage image = cropMinimapNaturalCoordinates(IOUtils.toByteArray(in), y1, x1, y2, x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile() + TOKYO_2_PNG);
        ImageIO.write(image, "png", pathFile);

        InputStream out = loader.getResourceAsStream(TOKYO_2_PNG);

        return IOUtils.toByteArray(Objects.requireNonNull(out));
    }
}
