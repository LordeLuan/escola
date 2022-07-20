package com.lordeluan.escola.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lordeluan.escola.dto.DisciplinaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disciplinas")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer semestre;

	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "disciplinas_alunos", joinColumns = @JoinColumn(name = "id_disciplina"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	@JsonIgnore
	private Set<Aluno> alunos;

	public Disciplina(DisciplinaDTO objDto) {
		this.id = objDto.getId();
		this.nome = objDto.getNome();
		this.semestre = objDto.getSemestre();
		this.professor = new Professor(objDto.getProfessorDTO());
	}

}
