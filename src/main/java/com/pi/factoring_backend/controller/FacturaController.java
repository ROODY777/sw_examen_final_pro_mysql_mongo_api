package com.pi.factoring_backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pi.factoring_backend.entity.Factura;
import com.pi.factoring_backend.service.FacturaService;

@Controller
@RequestMapping("/factura")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Factura>> listarFacturas(){
		List<Factura> listas = facturaService.listaFacturas();
		return ResponseEntity.ok(listas);
	}	
	
	@PostMapping("/registro")
	public ResponseEntity<?> registroFactura(@RequestBody List<Factura> facturas){
		try {
			
			Map<String, Object> salida = new HashMap<>();
	        List<String> lstMensajes = new ArrayList<>();
	        salida.put("errores", lstMensajes);
	        List<Factura> facturasRegistradas = facturaService.registroFacturas(facturas);
	        List<String> idsFacturas = facturasRegistradas.stream()
	                .map(Factura::getId)
	                .collect(Collectors.toList());
	        lstMensajes.add("Subasta registrada con id: "+idsFacturas);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(salida);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar: "+e.getMessage());
		}
	}
	
}
