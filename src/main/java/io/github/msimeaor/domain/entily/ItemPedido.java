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
    @NotNull(message = "O campo pedido é obrigatório!")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUTO")
    @NotNull(message = "O campo produto é obrigatório!")
    private Produto produto;

    @Column(nullable = false, name = "QUANTIDADE")
    @NotNull(message = "O campo quantidade é obrigatório")
    private Integer quantidade;

}
