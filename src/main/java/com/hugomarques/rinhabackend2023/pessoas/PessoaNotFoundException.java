package com.hugomarques.rinhabackend2023.pessoas;


import java.util.UUID;

public class PessoaNotFoundException extends RuntimeException {
    PessoaNotFoundException(UUID id) {
        super("Pessoa com id: " + id + " não encontrada.");
    }
}
