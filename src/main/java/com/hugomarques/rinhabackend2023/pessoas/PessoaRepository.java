package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(nativeQuery = true,
            value = "select p.* from pessoas p " +
                    "where p.apelido like %:term% or " +
                    "p.nome like %:term% or " +
                    "p.nascimento like %:term% or " +
                    "p.stack like %:term%")
    List<Pessoa> findAllByTerm(@Param("term") String term);

}
