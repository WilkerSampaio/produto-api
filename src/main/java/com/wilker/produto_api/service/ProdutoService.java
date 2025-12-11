package com.wilker.produto_api.service;

import com.wilker.produto_api.infrastructure.exception.ResourceNotFoundException;
import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import com.wilker.produto_api.infrastructure.mapper.ProdutoConverterMapper;
import com.wilker.produto_api.infrastructure.repository.CategoriaRepository;
import com.wilker.produto_api.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

   private final ProdutoRepository produtoRepository;
   private final ProdutoConverterMapper produtoConverterMapper;
   private final CategoriaRepository categoriaRepository;

   public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO){
      ProdutoEntity produtoEntity = produtoConverterMapper.paraProdutoEntity(produtoRequestDTO);

      CategoriaEntity categoriaEntity = categoriaRepository.findById(produtoRequestDTO.idCategoria())
              .orElseThrow(()-> new ResourceNotFoundException (
                      "Categoria não encontrada com ID: " + produtoRequestDTO.idCategoria()));

      produtoEntity.setCategoria(categoriaEntity);
      produtoRepository.save(produtoEntity);

      return produtoConverterMapper.paraProdutoResponse(produtoEntity);
   }
}