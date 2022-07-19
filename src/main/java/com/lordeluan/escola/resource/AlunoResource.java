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

import com.lordeluan.escola.dto.AlunoDTO;
import com.lordeluan.escola.entity.Aluno;
import com.lordeluan.escola.service.AlunoService;


@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll(){
		List<AlunoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) throws Exception{
		Aluno obj = service.findById(id);
		return ResponseEntity.ok().body(new AlunoDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Aluno> create(@Validated @RequestBody AlunoDTO cliDTO){
		Aluno cliente = service.create(cliDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @Validated @RequestBody AlunoDTO cliDTO) throws Exception{
		Aluno obj = service.update(id, cliDTO);
		return ResponseEntity.ok().body(new AlunoDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
