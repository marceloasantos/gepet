package br.com.fatec.pet.gepet.domain.model;

import br.com.fatec.pet.gepet.api.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ANI_ANIMAL")
public class Animal {
    public Animal(String nome, Double peso, Usuario dono) {
        this.nome = nome;
        this.peso = peso;
        this.dono = dono;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "ANI_ID")
    @JsonView(View.PetResumo.class)
    private UUID id;

    @Column(name = "ANI_NOME", length = 50, nullable = false)
    @JsonView(View.PetResumo.class)
    private String nome;

    @Column(name = "ANI_PESO", nullable = false)
    @JsonView(View.PetResumo.class)
    private Double peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @Type(type = "uuid-char")
    @JoinColumn(name = "ANI_USU_ID")
    @JsonView(View.PetCompleto.class)
    private Usuario dono;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "VAP_VACINA_APLICADA",
            joinColumns = { @JoinColumn(name = "VAP_ANI_ID") },
            inverseJoinColumns = { @JoinColumn(name = "VAP_VAC_ID") })
    @JsonView(View.PetCompleto.class)
    private Set<Vacina> vacinas;

    public Animal() {
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
