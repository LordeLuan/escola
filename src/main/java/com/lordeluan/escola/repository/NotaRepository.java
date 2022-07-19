package com.lordeluan.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{

	List<Nota> findNotaByDisciplinaAndAluno(Disciplina Disc, Aluno Aluno);

}
