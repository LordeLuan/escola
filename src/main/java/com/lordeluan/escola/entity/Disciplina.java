package com.lordeluan.escola.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lordeluan.escola.dto.DisciplinaDTO;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Integer semestre;

	@ManyToMany
	@JoinTable(name = "disciplinas_alunos",
			   joinColumns = @JoinColumn(name = "id_disciplina"),
				inverseJoinColumns = @JoinColumn(name ="id_aluno"))	
	@JsonIgnore
	private Set<Aluno> alunos;
	

	public Disciplina() {}
	
	public Disciplina(Long id, String nome, Integer semestre) {
		super();
		this.id = id;
		this.nome = nome;
		this.semestre = semestre;
	}

	public Disciplina(DisciplinaDTO objDto) {
		id = objDto.getId();
		nome = objDto.getNome();
		semestre = objDto.getSemestre();
		alunos = objDto.getAlunos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

}
