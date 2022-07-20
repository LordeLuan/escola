package com.lordeluan.escola.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private Set<Aluno> alunos;

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.EAGER)
	private Set<Nota> notas;

	public Disciplina(DisciplinaDTO objDto) {
		id = objDto.getId();
		nome = objDto.getNome();
		semestre = objDto.getSemestre();
		professor = new Professor(objDto.getProfessorDTO());
		notas = objDto.getNotas();
	}
	
	

}
