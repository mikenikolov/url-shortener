package com.example.urlshortener.controller;

import com.example.urlshortener.service.RedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final RedirectService redirectService;

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        response.sendRedirect(redirectService.findShortUrl(shortUrl).getOriginalUrl());
    }
}
