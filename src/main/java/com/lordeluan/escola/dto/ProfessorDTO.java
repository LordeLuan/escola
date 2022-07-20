package com.lordeluan.escola.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.entity.Professor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

	private Long id;
	private String nome;
	private Integer idade;
	private String cpf;
	private String cep;
	private String bairro;
	private String logradouro;
	private String cidade;
	private String email;
	private String usuario;
	private String senha;

	private Set<Disciplina> disciplinas;
	protected Set<Integer> perfis = new HashSet<>();

	public ProfessorDTO(Professor obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.idade = obj.getIdade();
		this.cpf = obj.getCpf();
		this.cep = obj.getCep();
		this.bairro = obj.getBairro();
		this.logradouro = obj.getLogradouro();
		this.cidade = obj.getCidade();
		this.email = obj.getEmail();
		this.usuario = obj.getUsuario();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.disciplinas = obj.getDisciplinas();
	}

}
