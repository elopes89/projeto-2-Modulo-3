package com.projeto2.modulo3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    @EqualsAndHashCode.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false)
    private String nome;

    // @Column
    private Double valorProduto;

    //@Column
    private Boolean StatusProduto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categoria"))
    private Categoria categoria;

    @JsonBackReference
    public Categoria getCategoria() {
        return categoria;
    }

}


