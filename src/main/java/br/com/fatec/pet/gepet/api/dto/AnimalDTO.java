package br.com.fatec.pet.gepet.api.dto;

import java.util.UUID;

public class AnimalDTO {
    private String nome;
    private double peso;
    private UUID idDono;

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

    public UUID getIdDono() {
        return idDono;
    }

    public void setIdDono(UUID idDono) {
        this.idDono = idDono;
    }
}
