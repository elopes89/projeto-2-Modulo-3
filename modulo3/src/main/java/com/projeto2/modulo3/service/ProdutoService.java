package com.projeto2.modulo3.service;

import com.projeto2.modulo3.model.Produto;
import com.projeto2.modulo3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public List<Produto> list() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Transactional
    public Produto getItemProdutoById(Long idProduto) {
        return produtoRepository.findById(idProduto).get();
    }

    public Double getTotalComprado() {
        return produtoRepository.getTotalComprado();
    }

}