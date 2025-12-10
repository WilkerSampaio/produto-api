package com.wilker.produto_api.infrastructure.dto.out;

import java.util.Set;

public record CategoriaResponseCompletoDTO(
        Long id,
        String nome,
        Set<Long> idsProdutos
) {
}
