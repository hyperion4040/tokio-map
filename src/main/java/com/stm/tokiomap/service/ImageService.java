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

        int xMin = (Math.max(Math.min((int)(x1*100),(int)(x2*100)),x1Natural)-x1Natural)*800/(x2Natural-x1Natural);
        int xMax = (Math.min(Math.max((int)(x1*100),(int)(x2*100)),x2Natural)-x1Natural)*800/(x2Natural-x1Natural);

        int yMin =  800 -(Math.max(Math.min((int)(y1*100),(int)(y2*100)),y2Natural)-y2Natural)*800/(y1Natural-y2Natural);
        int yMax =  800 -(Math.min(Math.max((int)(y1*100),(int)(y2*100)),y1Natural)-y2Natural)*800/(y1Natural-y2Natural);
        int weight = xMax - xMin;
        int height = yMin - yMax;

        int x = weight / 2 + xMin;
        int y = height / 2 + yMin;

        xMin = 0;
        xMax = 400;
        yMin = 0;
        yMax = 400;
        if (x == 0 || y == 0 || weight / 2 == 0 || height / 2 == 0) {
            return originalImage;
        } else {
            BufferedImage bufferedImage = originalImage.getSubimage(xMin, yMin, (xMax-xMin), (yMax-yMin));
            return Thumbnails.of(bufferedImage).size(800, 800).asBufferedImage();
//            return originalImage.getSubimage(x, y, weight, height);
        }
//        return originalImage;

    }

    public static BufferedImage cropMinimapNaturalCoordinates(byte[] image, float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

        int x1Natural = 13961;
        int y1Natural = 3582;

        int x2Natural = 13992;
        int y2Natural = 3554;

        /*int xMin = (Math.max(Math.min((int) (x1 * 100), (int) (x2 * 100)), x1Natural) - x1Natural) * 800 / (x2Natural - x1Natural);
        int xMax = (Math.min(Math.max((int) (x1 * 100), (int) (x2 * 100)), x2Natural) - x1Natural) * 800 / (x2Natural - x1Natural);

        int yMin = 800 - (Math.max(Math.min((int) (y1 * 100), (int) (y2 * 100)), y2Natural) - y2Natural) * 800 / (y1Natural - y2Natural);
        int yMax = 800 - (Math.min(Math.max((int) (y1 * 100), (int) (y2 * 100)), y1Natural) - y2Natural) * 800 / (y1Natural - y2Natural);*/

        int xMaxNat = Math.min(Integer.max((int) (x2*100), (int)(x1*100)), x2Natural)-x1Natural;
        int xMinNat = Math.max(Integer.min((int)(x1*100),(int)( x2*100)), x1Natural)-x1Natural;
        int yMaxNat = Math.min(Integer.max((int)(y2*100),(int)( y1*100)), y1Natural)-y2Natural;
        int yMinNat = Math.max(Integer.min((int)(y1*100),(int) (y2*100)), y2Natural)-y2Natural;

        int xMax = xMaxNat*800/(x2Natural - x1Natural);
        int xMin = xMinNat*800/(x2Natural-x1Natural);

        int yMax = 800-yMaxNat*800/(y1Natural-y2Natural);
        int yMin = 800-yMinNat*800/(y1Natural-y2Natural);

        Graphics2D g2d = originalImage.createGraphics();
        g2d.setColor(Color.BLACK);
        float thickness = 5f;

        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(xMin, yMax, xMax, yMin);
        g2d.dispose();
        return originalImage;
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

    public byte[] getMinimap(float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(TOKIO_PNG);

        BufferedImage image = cropMinimapNaturalCoordinates(IOUtils.toByteArray(in), y1, x1, y2, x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile() + TOKIO_1_PNG);
        ImageIO.write(image, "png", pathFile);

        InputStream out = loader.getResourceAsStream(TOKIO_1_PNG);

        return IOUtils.toByteArray(Objects.requireNonNull(out));
    }
}
