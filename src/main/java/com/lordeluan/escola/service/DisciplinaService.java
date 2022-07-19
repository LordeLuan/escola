package com.lordeluan.escola.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lordeluan.escola.dto.DisciplinaDTO;
import com.lordeluan.escola.dto.MatricularAluno;
import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.repository.AlunoRepository;
import com.lordeluan.escola.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Disciplina findById(Long id) throws Exception {
		 Optional<Disciplina> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new Exception("Objeto não encontrado id: " + id));
	}
	

	public List<DisciplinaDTO> findAll() {
		List<Disciplina> listDisciplina = repository.findAll();
		List<DisciplinaDTO> listDTO = listDisciplina.stream().map( x-> new DisciplinaDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	public DisciplinaDTO create(DisciplinaDTO objDto) {
		objDto.setId(null);

		return new DisciplinaDTO(repository.save(new Disciplina(objDto)));
	}

	public Disciplina update(Long id, DisciplinaDTO objDto) throws Exception {
		objDto.setId(id);
		Disciplina oldClientDto = findById(id);
		
		
		oldClientDto = new Disciplina(objDto);
		return repository.save(oldClientDto);
	}
	
	public void delete(Long id) {
		Disciplina obj = null;
		try {
			obj = findById(id);
		} catch (Exception e) {
			new Exception("Objeto não localizado");
		}
		repository.delete(obj);
	}
	
	public void matricularAluno(MatricularAluno mat) {
		Optional<Disciplina> disc = repository.findById(mat.getIdDisciplina());
		
		if(disc.get() != null) {
			
			Optional<Aluno> al = alunoRepository.findById(mat.getIdAluno());
			
			if(al.get().getId() > 0) {
				disc.get().getAlunos().add(al.get());
				repository.save(disc.get());
			}
		} 
		
		
	}
}
