package com.wilker.produto_api.infrastructure.dto;

import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseCompletoDTO;

import java.util.Set;

public class CategoriaResponseCompletoDTOFixture {

    public static CategoriaResponseCompletoDTO build(Long id,
                                                     String nome,
                                                     Set<Long> idsProdutos){
        return new CategoriaResponseCompletoDTO(id, nome, idsProdutos);
    }
}
