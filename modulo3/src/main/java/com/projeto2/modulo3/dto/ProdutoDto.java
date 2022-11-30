package com.projeto2.modulo3.dto;

import com.projeto2.modulo3.model.Categoria;
import lombok.Data;

@Data
public class ProdutoDto {
    private Long id;

    private String nome;

    private Double valor;

    private Boolean status;

    private Long id_categoria;



}
