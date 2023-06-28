package com.pi.factoring_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.factoring_backend.entity.Cartera;
import com.pi.factoring_backend.entity.Inversion;
import com.pi.factoring_backend.entity.Subasta;
import com.pi.factoring_backend.service.CarteraService;
import com.pi.factoring_backend.service.InversionService;
import com.pi.factoring_backend.service.SubastaService;
import com.pi.factoring_backend.util.MontoInvalidoException;
import com.pi.factoring_backend.util.PageResponse;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/inversion")
@CrossOrigin(origins = "http://localhost:4200")
public class InversionController {

	@Autowired
	private InversionService inversionService;

	@Autowired
	private SubastaService subastaService;

	@Autowired
	private CarteraService carteraService;

	@GetMapping("/usuario/{idusuario}")
	public ResponseEntity<PageResponse<Inversion>> inversionesDeUsuario(
			@PathVariable(name = "idusuario") Long idusuario,
			@RequestParam(name = "page", defaultValue = "1", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size
	) {
		return ResponseEntity.ok(inversionService.listarxCuentaUsuario(idusuario, page, size));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Inversion>> listarInversiones() {
		List<Inversion> lista = inversionService.listarInversiones();
		return ResponseEntity.ok(lista);
	}

	@Transactional
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarInversion(@RequestBody Inversion inversion) {
		try {
			Cartera cartera = carteraService.obtenerPorId(inversion.getCuentaUsuario().getId(), "PEN");
			Subasta subastaInvertida = subastaService.findById(inversion.getSubasta().getId());

			double montoInversion = inversion.getMonto();
			double montoCartera = cartera.getCantidad();
			double montoSubasta = subastaInvertida.getMontopendiente();

			if (montoInversion > montoCartera) {
				throw new MontoInvalidoException("No hay saldo disponible");
			}

			if (montoInversion > montoSubasta) {
				throw new MontoInvalidoException("La inversión supera a la subasta");
			}

			cartera.setCantidad(montoCartera - montoInversion);
			subastaInvertida.setMontopendiente(montoSubasta - montoInversion);

			carteraService.actualizarCantidad(cartera);
			subastaService.actualizarSubasta(subastaInvertida);
			Inversion inversionRegistrada = inversionService.registrarInversion(inversion);

			return ResponseEntity.status(HttpStatus.CREATED).body(inversionRegistrada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al registrar inversión: " + e.getMessage());
		}
	}

	// ROODY AGREGUE 18/06/2023
	
	  @GetMapping("/InversionesListaXIdCuentaUsuario/{idcuentaUsuario}") public
	  ResponseEntity<?> inversionListaXUsuario(@PathVariable Long idcuentaUsuario){
	  
	 
	 List<Inversion> cliente =
	  inversionService.findInversionXUsuarioId(idcuentaUsuario); if (cliente !=
	  null) { return ResponseEntity.ok(cliente); } return
	  ResponseEntity.notFound().build(); }
	 
	
	
	

	
	
	@GetMapping("/InversionesListaXIdCuentaUsuarioYXEstado")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaInversionesListaXIdCuentaUsuarioYXEstadoLike(
			@RequestParam(name = "idcuentaUsuario", required = true, defaultValue = "") Long idcuentaUsuario,
			@RequestParam(name = "estado", required = false, defaultValue = "") String estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
				List<Inversion> lista = inversionService.listaInversionesListaXIdCuentaUsuarioYXEstadoLike(idcuentaUsuario,estado );
			
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se consulto, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
	
	
	
	
}
