package com.toyproject.shortener.controller;

import com.toyproject.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ShortUrlController {
    private final UrlService urlService;

    @GetMapping("/{shortUrl}")
    public String redirectShortUrl(@PathVariable("shortUrl") String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        StringBuilder sb = new StringBuilder();
        sb.append("redirect:").append(originalUrl);
        return sb.toString();
    }
}
