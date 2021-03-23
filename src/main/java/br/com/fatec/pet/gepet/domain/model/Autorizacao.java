package br.com.fatec.pet.gepet.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AUT_AUTORIZACAO")
public class Autorizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "uuid-char")
    @Column(name = "AUT_ID")
    private UUID id;

    @Column(name = "AUT_NOME", length = 20, nullable = false)
    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
