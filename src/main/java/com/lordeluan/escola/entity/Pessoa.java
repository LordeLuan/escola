package com.lordeluan.escola.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lordeluan.escola.entity.enums.Perfil;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoas")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String nome;
	protected Integer idade;
	protected String cpf;
	protected String cep;
	protected String bairro;
	protected String logradouro;
	protected String cidade;
	protected String email;
	protected String usuario;
	protected String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	protected Set<Integer> perfis = new HashSet<>();

	// Percorre o atributo que é uma lista de Integer, chamando o metodo toEnum para
	// cada valor do array, converte o resultado do metodo
	// para uma lista tipo Set
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	// Recebe um perfil e adiciona a nossa lista de Integer o código que irá
	// retornar do enum de acordo com o perfil passado;
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
}
