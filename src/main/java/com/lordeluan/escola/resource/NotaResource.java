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

import com.lordeluan.escola.dto.MatricularAluno;
import com.lordeluan.escola.dto.NotaDTO;
import com.lordeluan.escola.entity.Nota;
import com.lordeluan.escola.service.NotaService;


@RestController
@RequestMapping(value = "/notas")
public class NotaResource {

	@Autowired
	private NotaService service;
	
	@GetMapping
	public ResponseEntity<List<NotaDTO>> findAll(){
		List<NotaDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotaDTO> findById(@PathVariable Long id) throws Exception{
		Nota obj = service.findById(id);
		return ResponseEntity.ok().body(new NotaDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Nota> create(@Validated @RequestBody NotaDTO cliDTO){
		Nota cliente = service.create(cliDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<NotaDTO> update(@PathVariable Long id, @Validated @RequestBody NotaDTO cliDTO) throws Exception{
		Nota obj = service.update(id, cliDTO);
		return ResponseEntity.ok().body(new NotaDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<NotaDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "disciplina/aluno")
	public ResponseEntity<List<NotaDTO>> findNotaByDisciplinaAndAluno(@RequestBody MatricularAluno alunoAndDisc){
		List<NotaDTO> list = service.findNotaByDisciplinaAndAluno(alunoAndDisc.getIdDisciplina(),alunoAndDisc.getIdAluno());
		return ResponseEntity.ok().body(list);
	}
	
}
