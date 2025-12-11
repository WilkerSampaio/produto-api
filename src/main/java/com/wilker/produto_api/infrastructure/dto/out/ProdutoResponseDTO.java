package com.wilker.produto_api.infrastructure.dto.out;

import java.math.BigDecimal;
public record ProdutoResponseDTO(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaResponseResumoDTO categoriaResponseResumoDTO
) {
}
