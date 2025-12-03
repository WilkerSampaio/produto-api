package com.wilker.produto_api.infrastructure.dto.in;

import lombok.Builder;

@Builder
public record CategoriaRequestDTO(
        String nome
) {
}
