package com.stm.tokiomap.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ImageService {

    BufferedImage getOriginalImage() throws IOException {
        final InputStream input = getClass().getClassLoader().getResourceAsStream("/tokio.png");
        return ImageIO.read(Objects.requireNonNull(input));
    }


}
