package com.lordeluan.escola.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lordeluan.escola.dto.AlunoDTO;
import com.lordeluan.escola.entity.enums.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "alunos")
	@JsonIgnore
	private Set<Disciplina> disciplinas;
	
//	@OneToMany(mappedBy = "aluno")
//	private Set<Nota> notas;
 
	public Aluno(AlunoDTO objDto) {
		this.id = objDto.getId();
		this.nome = objDto.getNome();
		this.idade = objDto.getIdade();
		this.cpf = objDto.getCpf();
		this.cep = objDto.getCep();
		this.bairro = objDto.getBairro();
		this.logradouro = objDto.getLogradouro();
		this.cidade = objDto.getCidade();
		this.email = objDto.getEmail();
		this.usuario = objDto.getUsuario();
		this.senha = objDto.getSenha();
		
//		this.disciplinas = objDto.getDisciplinas();
		this.perfis = objDto.getPerfis();
		addPerfil(Perfil.ALUNO);
	}

}