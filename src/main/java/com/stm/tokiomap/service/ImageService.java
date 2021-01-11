package com.stm.tokiomap.service;

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

    public static final String TOKIO_PNG = "/tokio.png";
    public static final String TOKIO_1_PNG = "tokio1.png";
    private final ClassLoader loader = getClass().getClassLoader();

    public static BufferedImage cropImageWithNaturalCoordinates(byte[] image, float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

        int x1Natural = 13961;
        int y1Natural = 3582;

        int x2Natural = 13992;
        int y2Natural = 3554;

        int xMin = (Math.max(Math.min((int)(x1*100),(int)(x2*100)),x1Natural)-x1Natural)*500/(x2Natural-x1Natural);
        int xMax = (Math.min(Math.max((int)(x1*100),(int)(x2*100)),x2Natural)-x1Natural)*500/(x2Natural-x1Natural);

        int weight = xMax - xMin;
        int yMin = (Math.max(Math.min((int)(y1*100),(int)(y2*100)),y2Natural)-y2Natural)*500/(y1Natural-y2Natural);
        int yMax = (Math.min(Math.max((int)(y1*100),(int)(y2*100)),y1Natural)-y2Natural)*500/(y1Natural-y2Natural);
        int height = yMax - yMin;

        int x = weight / 2 + xMin;
        int y = height / 2 + yMin;
        if (x == 0 || y == 0 || weight / 2 == 0 || height / 2 == 0) {
            return originalImage;
        } else {
            Graphics2D g2d = originalImage.createGraphics();
            g2d.setColor(Color.BLACK);
            float thickness = 5f;
            Stroke oldStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(xMin, yMin, xMax, yMax);
            g2d.dispose();
            return originalImage;
//            return originalImage.getSubimage(x, y, weight, height);
        }


    }

    public static BufferedImage cropImage(byte[] image, int y1, int x1, int y2, int x2) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

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

    public BufferedImage getOriginalImage() throws IOException {
        final InputStream input = loader.getResourceAsStream(TOKIO_PNG);
        return ImageIO.read(Objects.requireNonNull(input));
    }

    public byte[] getByteArrayFromImage() throws IOException {
        final InputStream input = loader.getResourceAsStream(TOKIO_PNG);
        return IOUtils.toByteArray(input);
    }

    public void saveImage(final BufferedImage image) throws IOException {
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile() + TOKIO_1_PNG);
        ImageIO.write(image, "png", pathFile);
    }

    public byte[] getModifiedImage(float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(TOKIO_PNG);


//        BufferedImage image = cropImage(IOUtils.toByteArray(in),(int)y1,(int)x1,(int)y2,(int) x2);
        BufferedImage image = cropImageWithNaturalCoordinates(IOUtils.toByteArray(in), y1, x1, y2, x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile() + TOKIO_1_PNG);
        ImageIO.write(image, "png", pathFile);

        InputStream out = loader.getResourceAsStream(TOKIO_1_PNG);

        return IOUtils.toByteArray(Objects.requireNonNull(out));
    }
}
