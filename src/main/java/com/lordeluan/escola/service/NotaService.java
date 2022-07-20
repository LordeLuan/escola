package com.lordeluan.escola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lordeluan.escola.dto.AlunoDTO;
import com.lordeluan.escola.dto.DisciplinaDTO;
import com.lordeluan.escola.dto.NotaDTO;
import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.entity.Nota;
import com.lordeluan.escola.repository.AlunoRepository;
import com.lordeluan.escola.repository.DisciplinaRepository;
import com.lordeluan.escola.repository.NotaRepository;

import javassist.NotFoundException;

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
		for (NotaDTO notaDTO : listDTO) {
			notaDTO.getDisciplinaDTO();
		}
		return listDTO;
	}

	public Nota create(NotaDTO objDto) throws NotFoundException {
		Optional<Aluno> alu = alunoRepository.findById(objDto.getAlunoDTO().getId());
		Optional<Disciplina> disc = discRepository.findById(objDto.getDisciplinaDTO().getId());

		if (alu.isPresent() && disc.isPresent()) {
			for (Disciplina di : alu.get().getDisciplinas()) {

				if (di.getId() == disc.get().getId()) {
					objDto.setId(null);
					objDto.setAlunoDTO(new AlunoDTO(alu.get()));
					objDto.setDisciplinaDTO(new DisciplinaDTO(disc.get()));
					Nota nota = new Nota(objDto);
					return repository.save(nota);

				} else {
					throw new NotFoundException(
							"Disciplina não encontrada nas disciplinas que o Aluno está matriculado!");
				}
			}
		}
		throw new NotFoundException("Objetos informados não localizados! AlunoID: " + objDto.getAlunoDTO().getId()
				+ " DisciplinaID: " + objDto.getDisciplinaDTO().getId());

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
