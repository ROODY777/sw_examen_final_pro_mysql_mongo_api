package com.pi.factoring_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.factoring_backend.entity.Movimiento;
import com.pi.factoring_backend.service.MovimientoService;

@RestController
@RequestMapping("/movimiento")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;
	
	@GetMapping
	public ResponseEntity<List<Movimiento>> listarMovimientos(){
		List<Movimiento> lista = movimientoService.listarMovimientos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/numeroCuentaBancaria/{numeroCuentaBancaria}")
	public ResponseEntity<Page<Movimiento>> listarMovimientosXCuentaBancaria(
			@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
			@PathVariable(name = "numeroCuentaBancaria") String cuentaBancaria
	){
		Page<Movimiento> movimientos = movimientoService.listarxNumeroCuentaBancaria(cuentaBancaria, page, size);
		return ResponseEntity.ok(movimientos);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Movimiento>> listarMovimientosPorUsuario(@PathVariable Long usuarioId){
		List<Movimiento> lista = movimientoService.listarMovimientosPorUsuario(usuarioId);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<?> registrarMovimiento(@RequestBody Movimiento movimiento) {
		try {
			Movimiento movimientoRegistrado = movimientoService.registrarMovimiento(movimiento);
			return ResponseEntity.status(HttpStatus.CREATED).body(movimientoRegistrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar: " +e.getMessage());
		}
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<List<Movimiento>> listarMovimientosPorUsuarioYEstado(@RequestParam Long usuarioId, @RequestParam String estado){
		List<Movimiento> lista = movimientoService.listarMovimientosPorUsuarioYEstado(usuarioId, estado);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movimiento> buscarPorId(@PathVariable int id) {
		Movimiento movimiento = movimientoService.buscarPorId(id);
		if(movimiento != null) {
			return ResponseEntity.ok(movimiento);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Movimiento> actualizarMovimiento(@RequestBody Movimiento movimientoNuevo) {
		Movimiento movimiento = movimientoService.buscarPorId(movimientoNuevo.getId());
		movimiento.setEstado(movimientoNuevo.getEstado());
		movimientoService.actualizarEstado(movimiento);
		return ResponseEntity.ok(movimiento);
	}
	
	@GetMapping("/estado/{estado}")
	public ResponseEntity<List<Movimiento>> listarMovimientosPorEstado(@PathVariable String estado){
		List<Movimiento> lista = movimientoService.listarMovimientosPorEstado(estado);
		return ResponseEntity.ok(lista);
	}
}
