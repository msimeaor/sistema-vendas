package io.github.msimeaor.domain.entily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "ID")
    private UUID id;

    @Column(nullable = false, name = "NOME", length = 50)
    private String nome;

    @Column(nullable = false, unique = true, name = "CPF", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> listaPedidos;

    public Cliente() {}
    public Cliente(String nome) {
        this.nome = nome;
    }
    public Cliente(String nome, UUID id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: " +this.getNome() + " - ID: " +this.getId();
    }
}
