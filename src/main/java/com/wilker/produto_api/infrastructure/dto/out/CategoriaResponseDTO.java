package com.wilker.produto_api.infrastructure.dto.out;

import java.util.Set;

public record CategoriaResponseDTO(
        Long id,
        String nome,
        Set<Long> idsProdutos
) {
}
