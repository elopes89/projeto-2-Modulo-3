package com.projeto2.modulo3.repository;

import com.projeto2.modulo3.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select sum(p.valorProduto)  from Produto p where p.StatusProduto = true ")
    Double getTotalComprado();


}
