package com.lordeluan.escola.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lordeluan.escola.dto.ProfessorDTO;
import com.lordeluan.escola.entity.Professor;
import com.lordeluan.escola.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repository;
	
	public Professor findById(Long id) throws Exception {
		 Optional<Professor> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new Exception("Objeto não encontrado id: " + id));
	}
	

	public List<ProfessorDTO> findAll() {
		List<Professor> listProfessor = repository.findAll();
		List<ProfessorDTO> listDTO = listProfessor.stream().map( x-> new ProfessorDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	public Professor create(ProfessorDTO objDto) {
		Professor alu = new Professor(objDto);
		alu.setId(null);
		return repository.save(alu);
	}

	public Professor update(Long id, ProfessorDTO objDto) throws Exception {
		objDto.setId(id);
		Professor oldClientDto = findById(id);
		objDto.setNome(objDto.getNome());
		objDto.setIdade(objDto.getIdade());
		objDto.getDisciplinas().addAll(objDto.getDisciplinas());
		oldClientDto = new Professor(objDto);
		return repository.save(oldClientDto);
	}
	
	public void delete(Long id) {
		Professor obj = null;
		try {
			obj = findById(id);
		} catch (Exception e) {
			new Exception("Objeto não localizado");
		}
		repository.delete(obj);
	}
}
