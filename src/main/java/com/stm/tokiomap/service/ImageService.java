package com.stm.tokiomap.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
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

    public BufferedImage getOriginalImage() throws IOException {
        final InputStream input = loader.getResourceAsStream(TOKIO_PNG);
        return ImageIO.read(Objects.requireNonNull(input));
    }

    public byte[] getByteArrayFromImage() throws IOException {
        final InputStream input = loader.getResourceAsStream(TOKIO_PNG);
        return IOUtils.toByteArray(input);
    }

    public void saveImage( final BufferedImage image) throws IOException {
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile()+ TOKIO_1_PNG);
        ImageIO.write(image,"png", pathFile);
    }

    public byte[] getModifiedImage(float y1, float x1, float y2, float x2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(TOKIO_PNG );




        BufferedImage image = cropImage(IOUtils.toByteArray(in),(int)y1,(int)x1,(int)y2,(int) x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile()+ TOKIO_1_PNG);
        ImageIO.write(image,"png", pathFile);

        InputStream out = loader.getResourceAsStream(TOKIO_1_PNG);

        return IOUtils.toByteArray(Objects.requireNonNull(out));
    }

    public static BufferedImage cropImage(byte[] image, int y1, int x1,int y2,int x2) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

        int xMax = Math.min(Integer.max(x2, x1), 500);
        int xMin = Math.max(Integer.min(x1,x2),0);
        int weight = xMax - xMin;
        int yMax = Math.min(Integer.max(y2, y1), 500);
        int yMin = Math.max(Integer.min(y1,y2),0);
        int height = yMax - yMin;
        int x = weight/2 + xMin;
        int y = height/2 + yMin;
        return originalImage.getSubimage(x, y, weight, height);
    }
}
