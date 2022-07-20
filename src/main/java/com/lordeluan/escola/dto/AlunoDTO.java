package com.lordeluan.escola.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
public class AlunoDTO {

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

//	private Set<Disciplina> disciplinas;
	protected Set<Integer> perfis = new HashSet<>();

	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
		idade = obj.getIdade();
		cpf = obj.getCpf();
		cep = obj.getCep();
		bairro = obj.getBairro();
		logradouro = obj.getLogradouro();
		cidade = obj.getCidade();
		email = obj.getEmail();
		usuario = obj.getUsuario();
		senha = obj.getSenha();
		perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
//		disciplinas = obj.getDisciplinas();
	}

}
