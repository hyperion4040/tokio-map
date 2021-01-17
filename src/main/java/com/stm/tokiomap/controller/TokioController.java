package com.stm.tokiomap.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.stm.tokiomap.service.ImageService.cropImage;

@Controller
public class TokioController {

    @GetMapping(
            value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam int x1, @RequestParam int y1, @RequestParam int x2, @RequestParam int y2) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/tokyo.png");
        final ClassLoader loader = getClass().getClassLoader();
        BufferedImage image = cropImage(IOUtils.toByteArray(in),y1,x1,y2,x2);
        File pathFile = new File(Objects.requireNonNull(loader.getResource(".")).getFile()+"tokyo1.png");
        ImageIO.write(image,"png", pathFile);

        InputStream out = loader.getResourceAsStream("tokyo1.png");

        return IOUtils.toByteArray(Objects.requireNonNull(out));

    }






}
