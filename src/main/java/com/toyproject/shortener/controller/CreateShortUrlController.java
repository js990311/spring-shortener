package com.toyproject.shortener.controller;


import com.toyproject.shortener.dto.CreateUrlForm;
import com.toyproject.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CreateShortUrlController {

    private final UrlService urlService;

    @PostMapping("/create")
    public String createShortUrl(@RequestBody CreateUrlForm url){
        String newUrl = urlService.createNewUrl(url.getUrl());
        return newUrl;
    }
}
