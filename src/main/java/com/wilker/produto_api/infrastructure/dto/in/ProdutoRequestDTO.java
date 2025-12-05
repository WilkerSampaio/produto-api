package com.wilker.produto_api.infrastructure.dto.in;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoRequestDTO(

        String nome,
        String descricao,
        BigDecimal preco,
        Long idCategoria
) {
}
