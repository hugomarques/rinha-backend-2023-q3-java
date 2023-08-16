package com.hugomarques.rinhabackend2023.pessoas;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 32)
    private String apelido;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private String nascimento;
    @Column(nullable = true)
    @Convert(converter = StringListConverter.class)
    private List<String> stack;

    public Pessoa() {
    }

    public Pessoa(String apelido, String nome, String nascimento, List<String> stack) {
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", apelido='" + apelido + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", stack=" + stack +
                '}';
    }
}
