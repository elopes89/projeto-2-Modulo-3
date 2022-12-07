package com.projeto2.modulo3.service;

import com.projeto2.modulo3.model.Produto;
import com.projeto2.modulo3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Transactional
    public Double getTotal() {
        Double totalLista = 0.0;
        List<Produto> lista = produtoRepository.findAll();
        for (Produto produto : lista) {
            if (produto.getStatusProduto() == true) {
                totalLista += produto.getValorProduto();
            }
        }
        return totalLista;
    }
}
