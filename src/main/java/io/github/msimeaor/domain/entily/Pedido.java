package io.github.msimeaor.domain.entily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "ID")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;

    @Column(nullable = false, name = "DATA_PEDIDO")
    private LocalDate data_pedido;

    @Column(nullable = false, name = "TOTAL", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedidos;

    @Override
    public String toString() {
        return "ID: " +this.getId() + "\n" +
                "DATA: " + this.getData_pedido() + "\n" +
                "VALOR TOTAL: " + this.getTotal() + "\n";
    }
}
