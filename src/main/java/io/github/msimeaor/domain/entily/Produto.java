package io.github.msimeaor.domain.entily;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "ID")
    private UUID id;

    @Column(nullable = false, name = "DESCRICAO", length = 100)
    private String descricao;

    @Column(nullable = false, name = "PRECO",  precision = 20, scale = 2)
    private BigDecimal preco;

    public Produto() {

    }
    public Produto(String descricao) {
        this.descricao = descricao;
    }
    public Produto(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

}
