package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PessoaAsyncService {

    @Autowired
    private PessoaRepository repository;

    @Async
    public void insert(Pessoa pessoa) {
        repository.save(pessoa);
    }

}
