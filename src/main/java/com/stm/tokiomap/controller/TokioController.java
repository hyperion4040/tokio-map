package com.stm.tokiomap.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Controller
public class TokioController {

    @GetMapping(
            value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam int x1, @RequestParam int y1, @RequestParam int x2, @RequestParam int y2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/tokio.png");
        final ClassLoader loader = getClass().getClassLoader();
        BufferedImage image = cropImage(IOUtils.toByteArray(in),y1,x1,y2,x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile()+"tokio1.png");
        ImageIO.write(image,"png", pathFile);

        InputStream out = loader.getResourceAsStream("tokio1.png");

        return IOUtils.toByteArray(Objects.requireNonNull(out));

    }

    public static BufferedImage cropImage(byte[] image, int y1, int x1,int y2,int x2) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);
//        int x = originalImage.getHeight()/2;
//        int y = originalImage.getHeight()/2;
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
