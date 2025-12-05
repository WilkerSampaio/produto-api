package com.wilker.produto_api.service;

import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseProdutoDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.mapper.CategoriaConverterMapper;
import com.wilker.produto_api.infrastructure.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaConverterMapper categoriaConverterMapper;

    public CategoriaResponseProdutoDTO criarCategoria(CategoriaRequestDTO categoriaRequestDTO){

        CategoriaEntity categoriaEntity = categoriaConverterMapper.paraCategoriaEntity(categoriaRequestDTO);

        return categoriaConverterMapper.paraCategoriaResponseProduto(categoriaRepository.save(categoriaEntity));


    }
}

