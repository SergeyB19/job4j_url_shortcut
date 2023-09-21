package com.example.job4j_url_shortcut.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UrlStatDto {
    @NonNull
    private String url;
    @NonNull
    private int total;
}
