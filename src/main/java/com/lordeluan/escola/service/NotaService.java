package com.lordeluan.escola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lordeluan.escola.dto.NotaDTO;
import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.entity.Nota;
import com.lordeluan.escola.repository.AlunoRepository;
import com.lordeluan.escola.repository.DisciplinaRepository;
import com.lordeluan.escola.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository repository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private DisciplinaRepository discRepository;

	public Nota findById(Long id) throws Exception {
		Optional<Nota> obj = repository.findById(id);
		return obj.orElseThrow(() -> new Exception("Objeto não encontrado id: " + id));
	}

	public List<NotaDTO> findAll() {
		List<Nota> listNota = repository.findAll();
		List<NotaDTO> listDTO = listNota.stream().map(x -> new NotaDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	public Nota create(NotaDTO objDto) {
		Nota alu = new Nota(objDto);
		alu.setId(null);
		return repository.save(alu);
	}

	public Nota update(Long id, NotaDTO objDto) throws Exception {
		objDto.setId(id);
		Nota oldClientDto = findById(id);
		oldClientDto.setDescricao(objDto.getDescricao());
		oldClientDto.setValor(objDto.getValor());
		oldClientDto.setAluno(new Aluno(objDto.getAlunoDTO()));
		oldClientDto.setDisciplina(new Disciplina(objDto.getDisciplinaDTO()));
		return repository.save(oldClientDto);
	}

	public void delete(Long id) {
		Nota obj = null;
		try {
			obj = findById(id);
		} catch (Exception e) {
			new Exception("Objeto não localizado");
		}
		repository.delete(obj);
	}

	public List<NotaDTO> findNotaByDisciplinaAndAluno(Long idDisc, Long idAluno) {
		Optional<Aluno> alu = alunoRepository.findById(idAluno);
		Optional<Disciplina> disc = discRepository.findById(idDisc);

		if (alu.get() != null && disc.get() != null) {
			List<Nota> listNota = repository.findNotaByDisciplinaAndAluno(disc.get(), alu.get());
			List<NotaDTO> listDTO = listNota.stream().map(x -> new NotaDTO(x)).collect(Collectors.toList());
			return listDTO;
		}
		List<NotaDTO> list = new ArrayList<>();
		return list;
	}
}
