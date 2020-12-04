package com.stm.tokiomap.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        /*File imageFile = new File("tokio.png");
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        BufferedImage image = cropImage(bufferedImage,bufferedImage.getWidth()/2,bufferedImage.getHeight()/2,100,100);
        File pathFile = new File("tokio1.png");
        ImageIO.write(image,"png", pathFile);*/
        InputStream in = getClass()
                .getResourceAsStream("/tokio.png");
        return IOUtils.toByteArray(in);
    }

    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height){
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
        return croppedImage;
    }
}
