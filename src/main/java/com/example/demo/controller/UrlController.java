package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UrlDto;
import com.example.demo.model.Url;
import com.example.demo.service.UrlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/urls")
@Tag(name = "URL Shortener", description = "API para gestionar URLs acortadas")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    @Operation(summary = "Acortar una URL", description = "Genera una URL corta a partir de una URL larga.")
    public Url shortenUrl(@RequestBody UrlDto urlDto) {
        return urlService.generateShortUrl(urlDto);
    }

    @GetMapping("/{shortUrl}")
    @Operation(summary = "Recuperar URL original", description = "Recupera la URL original a partir de una URL acortada.")
    public Url getOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
    @GetMapping("/status")
    public String getStatus() {
        return "Up and Running";
    }
}