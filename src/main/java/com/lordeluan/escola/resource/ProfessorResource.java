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

import com.lordeluan.escola.dto.ProfessorDTO;
import com.lordeluan.escola.entity.Professor;
import com.lordeluan.escola.service.ProfessorService;


@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {

	@Autowired
	private ProfessorService service;
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> findAll(){
		List<ProfessorDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfessorDTO> findById(@PathVariable Long id) throws Exception{
		Professor obj = service.findById(id);
		return ResponseEntity.ok().body(new ProfessorDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Professor> create(@Validated @RequestBody ProfessorDTO cliDTO){
		Professor cliente = service.create(cliDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable Long id, @Validated @RequestBody ProfessorDTO cliDTO) throws Exception{
		Professor obj = service.update(id, cliDTO);
		return ResponseEntity.ok().body(new ProfessorDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProfessorDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
