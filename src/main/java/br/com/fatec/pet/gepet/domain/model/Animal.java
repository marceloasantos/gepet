package br.com.fatec.pet.gepet.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ANI_ANIMAL")
public class Animal {
    public Animal(UUID id, String nome, Double peso, Usuario dono, Set<Vacina> vacinas) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.dono = dono;
        this.vacinas = vacinas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "ANI_ID")
    private UUID id;

    @Column(name = "ANI_NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "ANI_PESO", nullable = false)
    private Double peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @Type(type = "uuid-char")
    @JoinColumn(name = "ANI_USU_ID")
    private Usuario dono;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "VAP_VACINA_APLICADA",
            joinColumns = { @JoinColumn(name = "VAP_ANI_ID") },
            inverseJoinColumns = { @JoinColumn(name = "VAP_VAC_ID") })
    private Set<Vacina> vacinas;

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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Set<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(Set<Vacina> vacinas) {
        this.vacinas = vacinas;
    }
}
