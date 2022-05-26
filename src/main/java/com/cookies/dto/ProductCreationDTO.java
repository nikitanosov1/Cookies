package com.cookies.dto;

import lombok.Getter;

@Getter
public class ProductCreationDTO {

    // В ДТО и параметрах методов контроллеров я бы хотел, чтобы была добавлена валидация
    private final String name;

    public ProductCreationDTO(String name) {
        this.name = name;
    }
}
