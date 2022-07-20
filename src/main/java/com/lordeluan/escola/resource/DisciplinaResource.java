package com.lordeluan.escola.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lordeluan.escola.dto.DisciplinaDTO;
import com.lordeluan.escola.dto.MatricularAluno;
import com.lordeluan.escola.entity.Disciplina;
import com.lordeluan.escola.service.DisciplinaService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService service;

	@GetMapping
	public ResponseEntity<List<DisciplinaDTO>> findAll() {
		List<DisciplinaDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DisciplinaDTO> findById(@PathVariable Long id) throws Exception {
		Disciplina obj = service.findById(id);
		return ResponseEntity.ok().body(new DisciplinaDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Disciplina> create(@Validated @RequestBody DisciplinaDTO cliDTO) throws NotFoundException {
		try {
			DisciplinaDTO clienteDTO = service.create(cliDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			throw new NotFoundException("Objeto não entrado");
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DisciplinaDTO> update(@PathVariable Long id, @Validated @RequestBody DisciplinaDTO cliDTO)
			throws Exception {
		Disciplina obj = service.update(id, cliDTO);
		return ResponseEntity.ok().body(new DisciplinaDTO(obj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DisciplinaDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path = "/matricular")
	public void matricular(@RequestBody MatricularAluno cliDTO) {
		service.matricularAluno(cliDTO);
	}

}
