package br.com.fatec.pet.gepet.api.dto;

import java.util.UUID;

public class EditarAnimalDTO {
    private UUID id;
    private String nome;
    private double peso;

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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
