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
import javax.persistence.OneToMany;
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
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "disciplinas_alunos", 
				joinColumns = @JoinColumn(name = "id_disciplina"), 
				inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	@JsonIgnore
	private Set<Aluno> alunos;

	@OneToMany(mappedBy = "disciplina")
	private Set<Nota> notas;

	public Disciplina(DisciplinaDTO objDto) {
		id = objDto.getId();
		nome = objDto.getNome();
		semestre = objDto.getSemestre();
		alunos = objDto.getAlunos();
		professor = new Professor(objDto.getProfessorDTO());
	}
	
	

}
