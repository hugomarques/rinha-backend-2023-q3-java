package com.hugomarques.rinhabackend2023;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.hugomarques.rinhabackend2023.pessoas.Pessoa;
import com.hugomarques.rinhabackend2023.pessoas.PessoaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PessoaRepository repository) {

        return args -> {
//            repository.save(new Pessoa(UUID.randomUUID(), "Frodo Baggins", "thies", "1970-01-01", List.of("C#", "Java"))).doOnNext(item -> log.info("Item: " + item)).subscribe();
//            repository.save(new Pessoa(UUID.randomUUID(), "Bilbo Baggins", "burglar", "1970-01-01", List.of("Java"))).doOnNext(item -> log.info("Item: " + item)).subscribe();
            repository.findAllByTerm("gin").doOnNext(item -> log.info("Item: " + item)).subscribe();
        };
    }
}
