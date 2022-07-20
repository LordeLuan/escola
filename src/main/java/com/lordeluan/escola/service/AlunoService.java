package com.lordeluan.escola.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lordeluan.escola.dto.AlunoDTO;
import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	public Aluno findById(Long id) throws Exception {
		 Optional<Aluno> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new Exception("Objeto não encontrado id: " + id));
	}
	

	public List<AlunoDTO> findAll() {
		List<Aluno> listAluno = repository.findAll();
		List<AlunoDTO> listDTO = listAluno.stream().map( x-> new AlunoDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	public Aluno create(AlunoDTO objDto) {
		Aluno alu = new Aluno(objDto);
		alu.setId(null);
		return repository.save(alu);
	}

	public Aluno update(Long id, AlunoDTO objDto) throws Exception {
		objDto.setId(id);
		Aluno oldClientDto = findById(id);
		objDto.setNome(objDto.getNome());
		objDto.setIdade(objDto.getIdade());
//		objDto.getDisciplinas().addAll(objDto.getDisciplinas());
		oldClientDto = new Aluno(objDto);
		return repository.save(oldClientDto);
	}
	
	public void delete(Long id) {
		Aluno obj = null;
		try {
			obj = findById(id);
		} catch (Exception e) {
			new Exception("Objeto não localizado");
		}
		repository.delete(obj);
	}
}
