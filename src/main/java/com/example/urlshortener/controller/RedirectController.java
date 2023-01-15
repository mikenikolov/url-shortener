package com.example.urlshortener.controller;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.exception.RedirectException;
import com.example.urlshortener.respository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlRepository urlRepository;

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Optional<Url> urlFromDb = urlRepository.findByShortUrl(shortUrl);
        if (urlFromDb.isEmpty()) {
            throw new RedirectException("This url is not exists");
        }
        response.sendRedirect(urlFromDb.get().getOriginalUrl());
    }
}
