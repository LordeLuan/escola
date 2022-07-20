package com.lordeluan.escola.dto;

import java.util.Set;

import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {

	private Long id;
	private String nome;
	private Integer semestre;
	private ProfessorDTO professorDTO;
	private Set<Aluno> alunos;

	public DisciplinaDTO(Disciplina obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.semestre = obj.getSemestre();
		this.alunos = obj.getAlunos();
		this.professorDTO = new ProfessorDTO(obj.getProfessor());
	}
}
