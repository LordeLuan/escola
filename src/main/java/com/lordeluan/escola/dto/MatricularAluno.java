package com.lordeluan.escola.dto;

public class MatricularAluno {
	
	private Long idDisciplina;
	private Long idAluno;
	
	public MatricularAluno() {}
	
	public MatricularAluno(Long idDisciplina, Long idAluno) {
		super();
		this.idDisciplina = idDisciplina;
		this.idAluno = idAluno;
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	
	

}
