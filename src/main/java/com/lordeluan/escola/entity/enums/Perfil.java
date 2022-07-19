package com.lordeluan.escola.entity.enums;

public enum Perfil {

	ADMIN(0, "ADMIN"), ALUNO(1, "ALUNO"), PROFESSOR(2, "PROFESSOR");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
//		Percerro os valores do enum para localizar a qual valor o código passado se refere
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Perfil inválido");
	}
}
