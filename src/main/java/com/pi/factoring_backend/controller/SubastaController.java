package com.pi.factoring_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.factoring_backend.entity.Subasta;

import com.pi.factoring_backend.service.SubastaService;

@RestController
@RequestMapping("/subasta")
@CrossOrigin(origins = "http://localhost:4200")
public class SubastaController {
	
	@Autowired
	private SubastaService subastaService;	
	
	@GetMapping("/subastaListar")
	public ResponseEntity<List<Subasta>> listaCuentas(){
		List<Subasta> listaCuentas = subastaService.listarSubasta();
		return ResponseEntity.ok(listaCuentas);
	}	
	
	@PostMapping("/subastaRegistrar")
	public ResponseEntity<?> create(@Validated @RequestBody Subasta deudor,
			BindingResult result) {
		
		Subasta deudorNew = null;
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "'" + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			deudorNew = subastaService.registrarSubasta(deudor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La subasta ha sido creado con éxito!");
		response.put("subasta", deudorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/registro")
	public ResponseEntity<?> registrarSubasta(@RequestBody Subasta subasta){
		try {
	        Subasta subastaRegistrada = subastaService.registrarSubasta(subasta);	
			return ResponseEntity.status(HttpStatus.CREATED).body(subastaRegistrada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar: "+e.getMessage());
		}
	}
	
}
