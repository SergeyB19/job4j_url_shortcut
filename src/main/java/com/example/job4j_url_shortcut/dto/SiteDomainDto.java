package com.example.job4j_url_shortcut.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SiteDomainDto {
    @NotBlank(message = "Site domain must not be empty")
    private String site;
}
