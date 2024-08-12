package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UrlDto;
import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Encoder;
import com.example.demoexception.UrlNotFoundException;

import java.time.LocalDateTime;


@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;
    
    public Url generateShortUrl(UrlDto urlDto) {
    	 	String shortCode = Base62Encoder.generateShortCode();
    	    Url url = new Url();
    	    url.setOriginalUrl(urlDto.getOriginalUrl());
    	    url.setShortUrl(shortCode);
    	    url.setCreatedAt(LocalDateTime.now());
    	    url.setExpiresAt(LocalDateTime.now().plusDays(urlDto.getExpiresAt()));
    	    return urlRepository.save(url);
    }

    public Url getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("URL no encontrada"));
    }

}