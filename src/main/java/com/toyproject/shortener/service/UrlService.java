package com.toyproject.shortener.service;

import com.toyproject.shortener.domain.Url;
import com.toyproject.shortener.encoder.Base62Encoder;
import com.toyproject.shortener.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UrlService {

    private final UrlRepository urlRepository;

    public String createNewUrl(String originalUrl){
        Url url = Url.createUrl(originalUrl);
        urlRepository.save(url);
        return Base62Encoder.encode(url.getId());
    }

    public String getOriginalUrl(String shortUrl){
        long id = Base62Encoder.decode(shortUrl);
        Url url = urlRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        String ret = url.getOriginalUrl();
        return url.getOriginalUrl();
    }
}
