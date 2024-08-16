package com.example.demo.dto;

import lombok.Data;

@Data
public class UrlDto {
    
	private String originalUrl;
	
	private int expiresAt;

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public int getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

}