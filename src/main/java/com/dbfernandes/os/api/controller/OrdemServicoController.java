package com.dbfernandes.os.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbfernandes.os.api.model.OrdemServicoInput;
import com.dbfernandes.os.api.model.OrdemServicoRepresentationModel;
import com.dbfernandes.os.domain.model.OrdemServico;
import com.dbfernandes.os.domain.repository.OrdemServicoRepository;
import com.dbfernandes.os.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private OrdemServicoRepresentationModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		
		return toModel(gestaoOrdemServico.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoRepresentationModel> listar() {
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoRepresentationModel> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			OrdemServicoRepresentationModel ordemServicoModel = toModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	private OrdemServicoRepresentationModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoRepresentationModel.class);
	}
	
	private List<OrdemServicoRepresentationModel> toCollectionModel(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
}
