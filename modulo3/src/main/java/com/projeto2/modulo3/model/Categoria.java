package com.projeto2.modulo3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    @EqualsAndHashCode.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Produto> produtos = new ArrayList<Produto>();

    @JsonManagedReference
    public List<Produto> getProdutos() {
        return produtos;
    }

}
//    @OneToMany(targetEntity = Produto.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
//    private List<Produto> produtos;


