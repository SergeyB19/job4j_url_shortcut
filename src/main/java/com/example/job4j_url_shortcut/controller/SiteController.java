package com.example.job4j_url_shortcut.controller;

import com.example.job4j_url_shortcut.dto.SiteDomainDto;
import com.example.job4j_url_shortcut.dto.SiteDto;
import com.example.job4j_url_shortcut.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class SiteController {

    private final SiteService service;

    @PostMapping("/registration")
    public ResponseEntity<SiteDto> signUp(@Valid @NotNull @RequestBody SiteDomainDto siteDomainDto) {
        var siteDto = service.save(siteDomainDto);
        return new ResponseEntity<>(
                siteDto,
                HttpStatus.CREATED
        );
    }
}
