package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PessoaRowMapper implements RowMapper<Pessoa> {
    @Override
    public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getObject("id", UUID.class));
        pessoa.setApelido(rs.getString("apelido"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setNascimento(rs.getString("nascimento"));
        pessoa.setStack(StringListConverter.convert(rs.getString("stack")));
        return pessoa;
    }
}