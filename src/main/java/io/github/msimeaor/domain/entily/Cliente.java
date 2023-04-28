package io.github.msimeaor.domain.entily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, unique = true, name = "CPF", length = 14)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
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
