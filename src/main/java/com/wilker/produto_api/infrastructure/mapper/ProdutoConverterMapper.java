package com.wilker.produto_api.infrastructure.mapper;

import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProdutoConverterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    ProdutoEntity paraProdutoEntity (ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO paraProdutoResponse(ProdutoEntity produtoEntity);
}