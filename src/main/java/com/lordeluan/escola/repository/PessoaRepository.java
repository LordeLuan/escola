package com.lordeluan.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lordeluan.escola.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
