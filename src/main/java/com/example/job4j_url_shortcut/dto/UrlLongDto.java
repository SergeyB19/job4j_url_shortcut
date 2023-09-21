package com.example.job4j_url_shortcut.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UrlLongDto {
    @NotEmpty(message = "Url must not be empty")
    private String url;
}
