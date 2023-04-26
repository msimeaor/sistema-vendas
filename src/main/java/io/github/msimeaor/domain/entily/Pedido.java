package io.github.msimeaor.domain.entily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotNull(message = "O campo cliente é obrigatório!")
    private Cliente cliente;

    @Column(nullable = false, name = "DATA_PEDIDO")
    @NotNull(message = "O campo de data precisa ser preenchido!")
    @FutureOrPresent(message = "A data inserida precisa ser a data atual")
    private LocalDate data_pedido;

    @Column(nullable = false, name = "TOTAL", precision = 20, scale = 2)
    @NotNull(message = "O valor total precisa ser preenchido!")
    @Digits(integer = 20, fraction = 2)
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
