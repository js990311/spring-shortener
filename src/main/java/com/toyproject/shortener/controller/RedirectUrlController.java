package com.toyproject.shortener.controller;

import com.toyproject.shortener.service.UrlService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@Controller
public class RedirectUrlController {
    private final UrlService urlService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFoundException(){
        return "urlNotFound";
    }

    @GetMapping("/{shortUrl}")
    public String redirectShortUrl(@PathVariable("shortUrl") String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        StringBuilder sb = new StringBuilder();
        sb.append("redirect:").append(originalUrl);
        return sb.toString();
    }
}
