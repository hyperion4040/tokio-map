package com.stm.tokiomap.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class TokioController {

    @GetMapping(
            value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/tokio.png");
        final ClassLoader loader = getClass().getClassLoader();
        BufferedImage image = cropImage(IOUtils.toByteArray(in),200,200);
        File pathFile = new File(loader.getResource(".").getFile()+"tokio1.png");
        ImageIO.write(image,"png", pathFile);

        InputStream out = loader.getResourceAsStream("tokio1.png");

        return IOUtils.toByteArray(out);
    }

    public static BufferedImage cropImage(byte[] image, int width, int height) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);
        int x = originalImage.getWidth()/2;
        int y = originalImage.getHeight()/2;
        return originalImage.getSubimage(x, y, width, height);
    }




}
