package com.hugomarques.rinhabackend2023.pessoas;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CacheConfig(cacheNames = "PessoasCache")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @Autowired
    private RedisTemplate<String, Pessoa> cache;

    /**
     * Returns 201 for success and 401 if there's already a person with that same nickname.
     * 400 for invalid requests.
     */
    @PostMapping("/pessoas")
    public Mono<ResponseEntity<Pessoa>> newPessoa(@RequestBody Pessoa pessoa) {
        return Mono.fromCallable( () -> {
            if (cache.opsForValue().get(pessoa.getApelido()) != null) {
                return ResponseEntity.unprocessableEntity().build();
            }
            pessoa.setId(UUID.randomUUID());
            cache.opsForValue().set(pessoa.getApelido(), pessoa);
            cache.opsForValue().set(pessoa.getId().toString(), pessoa);
            repository.save(pessoa);
            return new ResponseEntity(pessoa, HttpStatus.CREATED);
        });
    }

    /**
     * 200 for OK
     * 404 for not found.
     */
    @GetMapping("/pessoas/{id}")
    public Mono<ResponseEntity<Pessoa>> getById(@PathVariable UUID id) {
        Pessoa cached = cache.opsForValue().get(id.toString());
        if (cached != null) {
            return Mono.just(ResponseEntity.ok(cached));
        }
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new PessoaNotFoundException(id)));
    }

    @GetMapping("/pessoas")
    public Mono<ResponseEntity<List<Pessoa>>> findAllBySearchTerm(@RequestParam(name = "t") String term) {
        if (term == null || term.isEmpty() || term.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return repository.findAllByTerm(term).collectList().map(ResponseEntity::ok);
    }

    @GetMapping("/contagem-pessoas")
    public Mono<ResponseEntity<String>> count() {
        return repository.count()
                .map(count -> ResponseEntity.ok(String.valueOf(count)));
    }



}
