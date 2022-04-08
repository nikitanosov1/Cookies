package com.cookies.dto;

import lombok.Getter;

@Getter
public class ProductCreationDTO {
    private final String name;

    public ProductCreationDTO(String name) {
        this.name = name;
    }
}
