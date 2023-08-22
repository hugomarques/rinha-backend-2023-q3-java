package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PessoaRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Pessoa> findAllByTerm(String term) {
        String query = "SELECT p.* FROM pessoas p " +
                "WHERE p.apelido LIKE :term OR " +
                "p.nome LIKE :term OR " +
                "p.nascimento LIKE :term OR " +
                "p.stack LIKE :term";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("term", "%" + term + "%");

        return jdbcTemplate.query(query, paramMap, new PessoaRowMapper());
    }

    public Optional<Pessoa> findById(UUID id) {
        String query = "SELECT * FROM pessoas WHERE id = :id";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        RowMapper<Pessoa> rowMapper = new PessoaRowMapper();
        try {
            var found = jdbcTemplate.queryForObject(query, paramMap, rowMapper);
            return Optional.of(found);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public int count() {
        String query = "SELECT COUNT(*) FROM pessoas";

        Map<String, Object> paramMap = new HashMap<>();

        return jdbcTemplate.queryForObject(query, paramMap, Integer.class);
    }

    public Pessoa save(Pessoa pessoa) {
        String query = "INSERT INTO pessoas (id, apelido, nome, nascimento, stack) " +
                "VALUES (:id, :apelido, :nome, :nascimento, :stack)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", pessoa.getId());
        paramMap.put("apelido", pessoa.getApelido());
        paramMap.put("nome", pessoa.getNome());
        paramMap.put("nascimento", pessoa.getNascimento());
        paramMap.put("stack", StringListConverter.convert(pessoa.getStack()));

        jdbcTemplate.update(query, paramMap);
        return pessoa;
    }
}
