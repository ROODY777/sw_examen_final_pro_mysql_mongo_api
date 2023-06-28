package com.pi.factoring_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.factoring_backend.entity.Bancomongo;
import com.pi.factoring_backend.service.BancomongoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class BancomongoController {

	
	private final BancomongoService bancoService;
	

	@PostMapping("/bancos")
	public ResponseEntity<?> create(@Validated  @RequestBody Bancomongo cuentaBancariaUsuario, BindingResult result)
	{
		Bancomongo cuentaBancariaUsuarioNew = null;
		Map<String, Object> response = new HashMap<>();

	 	
		if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					 .stream()
					 .map(err -> "El campo '"+ err.getField() + "'" + err.getDefaultMessage())
					 .collect(Collectors.toList());
			
			response.put("errors",errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		try {
			//roody comente 13_03_2023
			long numero = (long)(Math.random()*100000);
			cuentaBancariaUsuario.setId((long) numero);
			
			
			cuentaBancariaUsuarioNew = bancoService.registrarCuenta(cuentaBancariaUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "El banco ha sido creado con éxito!");
		response.put("cliente", cuentaBancariaUsuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	
	@GetMapping("/bancos")
	public List<Bancomongo> findAll() {
	return	bancoService.findAll();
	
	}
	
	@GetMapping("/bancos/{id}")
	public Bancomongo findById(@PathVariable Long id) {
	return	bancoService.findById(id).get();
	
	}
	
	
	@DeleteMapping("/bancos/{id}")
	public void deleteById(@PathVariable Long id) {
		bancoService.deleteById(id);
	}
	
	
	@PutMapping("/bancos")
	public void update(@RequestBody Bancomongo banco) {
		bancoService.save(banco);
	}
	
	
}
