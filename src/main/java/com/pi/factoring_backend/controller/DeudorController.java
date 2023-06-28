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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CargoEmpresaDeudor;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Deudor;

import com.pi.factoring_backend.service.DeudorService;

@RestController
@RequestMapping("/deudor")
@CrossOrigin(origins = "http://localhost:4200")
public class DeudorController {

	@Autowired
	private DeudorService deudorService;

	
	@GetMapping("/deudorListar")
	public ResponseEntity<List<Deudor>> listaCuentas(){
		List<Deudor> listaCuentas = deudorService.listarDeudor();
		return ResponseEntity.ok(listaCuentas);
	}
	
	
	@PostMapping("/deudorRegistrar")
	public ResponseEntity<?> create(@Validated @RequestBody Deudor deudor,
			BindingResult result) {
		Deudor deudorNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "'" + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			deudorNew = deudorService.registrarDeudor(deudor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El deudor ha sido creado con éxito!");
		response.put("deudor", deudorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/deudorEliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		Deudor cliente = deudorService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if(cliente == null) {
			response.put("mensaje", "Error: no se pudo eliminar el deudor ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			deudorService.eliminarDeudor(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El deudor ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/deudorActualizar/{id}")
	public ResponseEntity<?>  update(@Validated @RequestBody Deudor deudor, BindingResult result,
			@PathVariable Long id)
	{
		Deudor DeudorActual = deudorService.findById(id);
		Deudor DeudorUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
	if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					 .stream()
					 .map(err -> "El campo '"+ err.getField() + "'" + err.getDefaultMessage())
					 .collect(Collectors.toList());
			
			response.put("errors",errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if(DeudorActual == null) {
			response.put("mensaje", "Error: no se pudo editar el Deudor ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			DeudorActual.setNombre(deudor.getNombre());
			DeudorActual.setApellidos(deudor.getApellidos());
			DeudorActual.setCorreo(deudor.getCorreo());
			DeudorActual.setTelefono(deudor.getTelefono());

			DeudorActual.setCargoEmpresaDeudor(deudor.getCargoEmpresaDeudor());
			
			DeudorUpdate = deudorService.registrarDeudor(DeudorActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		response.put("mensaje", "El deudor ha sido actualizado con éxito!");
		response.put("cliente", DeudorUpdate); 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 

	}
	
	
	@GetMapping("/deudorBuscar/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Deudor cliente =  null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			 cliente = deudorService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		if(cliente == null) {
			response.put("mensaje", "El deudor ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Deudor>(cliente, HttpStatus.OK); 
		
	}
	
	
	
	
	//combo box
	@GetMapping("/cargoEmpresaDeudor")
	public List<CargoEmpresaDeudor> listarcargoEmpresaDeudor(){
		
		return deudorService.findAllcargoEmpresaDeudor();
	}
	

	
	
	
	
	
	
}
