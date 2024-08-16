package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.UrlDto;
import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlService;
import com.example.demo.utils.Base62Encoder;
import com.example.demoexception.UrlNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateShortUrl_NewUrl() {
        UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl("https://example.com");

        Url savedUrl = new Url();
        savedUrl.setId(1L);
        savedUrl.setOriginalUrl("https://example.com");
        savedUrl.setShortUrl(Base62Encoder.encode(savedUrl.getId()));
        savedUrl.setCreatedAt(LocalDateTime.now());
        savedUrl.setExpiresAt(LocalDateTime.now().plusDays(30));

        when(urlRepository.findByOriginalUrl(anyString())).thenReturn(Optional.empty());
        when(urlRepository.save(any(Url.class))).thenReturn(savedUrl);

        Url result = urlService.generateShortUrl(urlDto);

        assertNotNull(result);
        assertEquals("https://example.com", result.getOriginalUrl());
        assertEquals(Base62Encoder.encode(savedUrl.getId()), result.getShortUrl());

        verify(urlRepository, times(1)).findByOriginalUrl(anyString());
        verify(urlRepository, times(2)).save(any(Url.class));
    }

    @Test
    void testGenerateShortUrl_ExistingUrl() {
        UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl("https://example.com");

        Url existingUrl = new Url();
        existingUrl.setId(1L);
        existingUrl.setOriginalUrl("https://example.com");
        existingUrl.setShortUrl("abc123");
        existingUrl.setCreatedAt(LocalDateTime.now());
        existingUrl.setExpiresAt(LocalDateTime.now().plusDays(30));

        when(urlRepository.findByOriginalUrl(anyString())).thenReturn(Optional.of(existingUrl));

        Url result = urlService.generateShortUrl(urlDto);

        assertNotNull(result);
        assertEquals("https://example.com", result.getOriginalUrl());
        assertEquals("abc123", result.getShortUrl());

        verify(urlRepository, times(1)).findByOriginalUrl(anyString());
        verify(urlRepository, times(0)).save(any(Url.class));
    }

    @Test
    void testGetOriginalUrl_Success() {
        String shortUrl = "abc123";

        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl("https://example.com");
        url.setShortUrl(shortUrl);
        url.setCreatedAt(LocalDateTime.now());
        url.setExpiresAt(LocalDateTime.now().plusDays(30));

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(url));

        Url result = urlService.getOriginalUrl(shortUrl);

        assertNotNull(result);
        assertEquals("https://example.com", result.getOriginalUrl());

        verify(urlRepository, times(1)).findByShortUrl(shortUrl);
    }

    @Test
    void testGetOriginalUrl_NotFound() {
        String shortUrl = "abc123";

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.empty());

        assertThrows(UrlNotFoundException.class, () -> urlService.getOriginalUrl(shortUrl));

        verify(urlRepository, times(1)).findByShortUrl(shortUrl);
    }
}
