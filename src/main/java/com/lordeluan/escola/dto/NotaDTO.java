package com.lordeluan.escola.dto;

import com.lordeluan.escola.entity.Nota;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaDTO {

	private Long id;
	private String descricao;
	private Double valor;

	private AlunoDTO alunoDTO;
	private DisciplinaDTO disciplinaDTO;
	
	public NotaDTO(Nota obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.alunoDTO = new AlunoDTO(obj.getAluno());
		this.disciplinaDTO = new DisciplinaDTO(obj.getDisciplina());
	}

}
