package br.com.fatec.pet.gepet.domain.model;

import br.com.fatec.pet.gepet.api.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USU_USUARIO")
public class Usuario {
    public Usuario(String nome, String email, String senha, Set<Autorizacao> autorizacoes) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.autorizacoes = autorizacoes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "USU_ID")
    private UUID id;

    @Column(name = "USU_NOME", length = 50, nullable = false)
    @JsonView(View.PetCompleto.class)
    private String nome;

    @Column(name = "USU_EMAIL", length = 50, nullable = false)
    @JsonView(View.PetCompleto.class)
    private String email;

    @Column(name = "USU_SENHA")
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USA_USUARIO_AUTORIZACAO",
            joinColumns = { @JoinColumn(name = "USA_USU_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USA_AUT_ID") })
    private Set<Autorizacao> autorizacoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dono")
    private Set<Animal> animais;

    public Usuario() {
    }

    public Set<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(Set<Animal> animais) {
        this.animais = animais;
    }
}
