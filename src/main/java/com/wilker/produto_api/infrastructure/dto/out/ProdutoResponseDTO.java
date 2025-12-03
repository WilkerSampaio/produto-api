package com.wilker.produto_api.infrastructure.dto.out;

import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoResponseDTO(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaEntity categoria
) {
}
