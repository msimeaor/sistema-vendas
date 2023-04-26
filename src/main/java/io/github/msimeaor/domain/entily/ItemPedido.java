package io.github.msimeaor.domain.entily;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "ID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO")
    @NotNull(message = "{campo.codigo-pedido.obrigatorio}")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUTO")
    @NotNull(message = "{campo.codigo-produto.obrigatorio}")
    private Produto produto;

    @Column(nullable = false, name = "QUANTIDADE")
    @NotNull(message = "{campo.quantidade-itens.obrigatorio}")
    private Integer quantidade;

}
