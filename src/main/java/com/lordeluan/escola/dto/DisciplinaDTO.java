package com.lordeluan.escola.dto;

import java.util.Set;

import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;

public class DisciplinaDTO {
	
	private Long id;
	private String nome;
	private Integer semestre;
	private Set<Aluno> alunos;
	
	public DisciplinaDTO() {}
	
	public DisciplinaDTO(Long id, String nome, Integer semestre) {
		super();
		this.id = id;
		this.nome = nome;
		this.semestre = semestre;
	}
	
	public DisciplinaDTO(Disciplina obj) {
		id = obj.getId();
		nome = obj.getNome();
		semestre = obj.getSemestre();
		alunos = obj.getAlunos();
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
