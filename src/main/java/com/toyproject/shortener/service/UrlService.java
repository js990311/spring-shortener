package com.toyproject.shortener.service;

import com.toyproject.shortener.domain.Url;
import com.toyproject.shortener.dto.response.ShortUrlResponse;
import com.toyproject.shortener.encoder.Base62Encoder;
import com.toyproject.shortener.repository.UrlCacheRepository;
import com.toyproject.shortener.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlCacheRepository cacheRepository;

    public List<ShortUrlResponse> createUrls(List<String> createUrls){
        List<ShortUrlResponse> result = new ArrayList<>();
        for(String originalUrl : createUrls){
            String shortUrl = createNewUrl(originalUrl);
            result.add(new ShortUrlResponse(
                    originalUrl,
                    shortUrl
            ));
        }
        return result;
    }

    public String createNewUrl(String originalUrl){
        Url url = Url.createUrl(originalUrl);
        urlRepository.save(url);
        return Base62Encoder.encode(url.getId());
    }

    public String getOriginalUrl(String shortUrl){
        // redis로 캐시
        String originalUrl = cacheRepository.get(shortUrl);

        if(originalUrl != null){
            // cache에 있으면 cache값으로 사용함
            return originalUrl;
        }

        long id = Base62Encoder.decode(shortUrl);
        Url url = urlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        originalUrl = url.getOriginalUrl();

        // cache에 넣기
        cacheRepository.save(shortUrl, originalUrl);

        return originalUrl;
    }
}
