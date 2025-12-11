package com.wilker.produto_api.infrastructure.dto.in;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        String nome,
        String descricao,
        BigDecimal preco,
        Long idCategoria
) {
}
