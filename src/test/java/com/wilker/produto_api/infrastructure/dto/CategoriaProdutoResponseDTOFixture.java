package com.wilker.produto_api.infrastructure.dto;

import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;

public class CategoriaProdutoResponseDTOFixture {

    public static CategoriaResponseResumoDTO build(Long id,
                                                   String nome){
        return new CategoriaResponseResumoDTO(id, nome);
    }
}
