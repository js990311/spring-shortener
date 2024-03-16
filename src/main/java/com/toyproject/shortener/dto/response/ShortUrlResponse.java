package com.toyproject.shortener.dto.response;

import lombok.Getter;

@Getter
public class ShortUrlResponse {
    private String originalUrl;
    private String shortUrl;

    public ShortUrlResponse(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }
}
