package com.hugomarques.rinhabackend2023;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.hugomarques.rinhabackend2023.pessoas.Pessoa;
import com.hugomarques.rinhabackend2023.pessoas.PessoaRepository;

//@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//    @Bean
    CommandLineRunner initDatabase(PessoaRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Pessoa("Bilbo Baggins", "burglar", "1970-01-01", List.of("Java"))));
            log.info("Preloading " + repository.save(new Pessoa("Frodo Baggins", "thief", "1985-01-01", List.of("C#"))));
        };
    }
}
