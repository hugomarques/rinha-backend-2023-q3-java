package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @Autowired
    AsyncTaskExecutor taskExecutor;

    Map<UUID, Pessoa> localCache = new HashMap<>();

    /**
     * Returns 201 for success and 401 if there's already a person with that same nickname.
     * 400 for invalid requests.
     */
    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> newPessoa(@RequestBody Pessoa pessoa) {
        pessoa.setId(UUID.randomUUID());
        localCache.put(pessoa.getId(), pessoa);
        taskExecutor.submit( () -> repository.save(pessoa));
        return new ResponseEntity(pessoa, HttpStatus.CREATED);
    }

    /**
     * 200 for OK
     * 404 for not found.
     */
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable UUID id) {
        if (localCache.containsKey(id)) {
            return ResponseEntity.ok(localCache.get(id));
        }
        return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id)));
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> findAllBySearchTerm(@RequestParam(name = "t") String term) {
        if (term == null || term.isEmpty() || term.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findAllByTerm(term));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> count() {
        return ResponseEntity.ok(String.valueOf(repository.count()));
    }



}
