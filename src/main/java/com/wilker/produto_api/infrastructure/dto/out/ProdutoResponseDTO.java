package com.wilker.produto_api.infrastructure.dto.out;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoResponseDTO(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Long idCategoria
) {
}
