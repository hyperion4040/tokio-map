package com.stm.tokiomap.controller;

import com.stm.tokiomap.cityImage.GetImageRequest;
import com.stm.tokiomap.cityImage.GetImageResponse;
import com.stm.tokiomap.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;

@Endpoint
public class ImageEndpoint {

    private ImageService imageService;

    public ImageEndpoint(final ImageService imageService) {
        this.imageService = imageService;
    }

    @PayloadRoot(namespace = "http://akozlowski/soap",localPart = "getImageResponse")
    @ResponsePayload()
    public GetImageResponse getSpecifiedImage(@RequestPayload GetImageRequest iq) throws IOException {
        final byte[] image = imageService.getModifiedImage(iq.getY1(), iq.getX1(), iq.getY2(), iq.getX2());
        final GetImageResponse imageResponse = new GetImageResponse();
        imageResponse.setImage(image);
        return imageResponse;
    }
}
