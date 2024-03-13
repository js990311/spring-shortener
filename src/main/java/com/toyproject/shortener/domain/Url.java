package com.toyproject.shortener.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {
    @Id @GeneratedValue
    @Column(name = "url_id")
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    Url(String originalUrl){
        this.originalUrl = originalUrl;
    }

    public static Url createUrl(String originalUrl){
        Url url = new Url(originalUrl);
        return url;
    }
}
