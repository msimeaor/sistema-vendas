package io.github.msimeaor.domain.entily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;

    @Column(nullable = false, unique = true, name = "CPF", length = 14)
    @NotEmpty(message = "O campo CPF é obrigatório!")
    @CPF(message = "Informe um CPF válido!")
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
