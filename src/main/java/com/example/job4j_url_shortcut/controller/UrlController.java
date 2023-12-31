package com.example.job4j_url_shortcut.controller;

import com.example.job4j_url_shortcut.dto.UrlLongDto;
import com.example.job4j_url_shortcut.dto.UrlShortDto;
import com.example.job4j_url_shortcut.dto.UrlStatDto;
import com.example.job4j_url_shortcut.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService service;

    @PostMapping("/convert")
    public ResponseEntity<UrlShortDto> convert(@Valid @RequestBody UrlLongDto urlLongDto) {
        var urlShortDto = service.save(urlLongDto);
        return new ResponseEntity<>(
                urlShortDto,
                HttpStatus.OK
                );
    }

    @GetMapping("/redirect/{code}")
        public ResponseEntity<Void> redirect(@PathVariable String code) {
        var byShortUrl = service.findByShortUrl(code);
        if (byShortUrl.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var longUrl = byShortUrl.get().getLongUrl();
        service.updateCallsCounterByShortUrl(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<UrlStatDto>> getStatistic() {
        var listDto = service.findAllBySite();
        return new ResponseEntity<>(
                listDto,
                HttpStatus.OK
        );
    }
}
