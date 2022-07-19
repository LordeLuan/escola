package com.lordeluan.escola.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lordeluan.escola.dto.NotaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valor;
	
	@ManyToOne
	@JsonIgnore
	private Aluno aluno;
	
	@ManyToOne
	@JsonIgnore
	private Disciplina disciplina;
	
	public Nota(NotaDTO objDto) {
		this.id = objDto.getId();
		this.descricao = objDto.getDescricao();
		this.valor = objDto.getValor();
		
		this.aluno = new Aluno(objDto.getAlunoDTO());
		this.disciplina = new Disciplina(objDto.getDisciplinaDTO());
	}
	
}
