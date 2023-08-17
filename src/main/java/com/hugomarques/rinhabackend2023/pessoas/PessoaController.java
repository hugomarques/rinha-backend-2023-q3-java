package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CacheConfig(cacheNames = "PessoasCache")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaAsyncService pessoaAsyncService;

    @Autowired
    private RedisTemplate<String, Pessoa> cache;

//    Map<UUID, Pessoa> localCache = new HashMap<>();

    /**
     * Returns 201 for success and 401 if there's already a person with that same nickname.
     * 400 for invalid requests.
     */
    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> newPessoa(@RequestBody Pessoa pessoa) {
        if (cache.opsForValue().get(pessoa.getApelido()) != null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        pessoa.setId(UUID.randomUUID());
        cache.opsForValue().set(pessoa.getApelido(), pessoa);
        cache.opsForValue().set(pessoa.getId().toString(), pessoa);
        pessoaAsyncService.insert(pessoa);
        return new ResponseEntity(pessoa, HttpStatus.CREATED);
    }

    /**
     * 200 for OK
     * 404 for not found.
     */
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable UUID id) {
        Pessoa cached = cache.opsForValue().get(id.toString());
        if (cached != null) {
            return ResponseEntity.ok(cached);
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
