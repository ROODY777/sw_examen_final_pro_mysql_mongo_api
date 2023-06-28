package com.pi.factoring_backend.controller;


import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.entity.TipoCuentaBancaria;
import com.pi.factoring_backend.service.CuentaBancariaService;

@RestController
@RequestMapping("/cuenta") 
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaBancariaController {
	
	private final Logger log = LoggerFactory.getLogger(CuentaBancariaController.class);
	
	@Autowired
	private CuentaBancariaService cuentaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<CuentaBancariaUsuario>> listaCuentas(){
		List<CuentaBancariaUsuario> listaCuentas = cuentaService.listarCuentas();
		return ResponseEntity.ok(listaCuentas);
	}
	
	/*@PostMapping("/numeroCuenta/{numeroCuenta}")
	public ResponseEntity<CuentaBancariaUsuario> obtenerCuentaBancariaxNumeroCuenta(
			@PathVariable String numeroCuenta
	){
		return ResponseEntity.ok(cuentaService.buscarPorNumeroCuenta(numeroCuenta));
	}*/
	
	/*@PostMapping
	public ResponseEntity<String> registrarCuenta(@RequestBody CuentaBancariaUsuario cuentaBancaria) {
		try {
			CuentaBancariaUsuario cuentaRegistrada = cuentaService.registrarCuenta(cuentaBancaria);
			return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Cuenta id: %d registrada con éxito", cuentaRegistrada.getId()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar: " +e.getMessage());
		}
	}*/
	
	@GetMapping("/{id}")
    public ResponseEntity<List<CuentaBancariaUsuario>> obtenerCuentaBancaria(@PathVariable Long id) {
        List<CuentaBancariaUsuario> cuentaBancaria = cuentaService.buscarPorUsuario(id);
        if (cuentaBancaria != null) {
        	return ResponseEntity.ok(cuentaBancaria);
        }
        return ResponseEntity.notFound().build();
    }
// Michaell

/*
	@GetMapping("/admin/{banco}/{moneda}")
	public ResponseEntity<CuentaBancariaUsuario> listarPorAdmin(@PathVariable("banco")String banco, @PathVariable("moneda")String moneda){
		String decodedBanco = URLDecoder.decode(banco, StandardCharsets.UTF_8);
		CuentaBancariaUsuario cuentaAdmin = cuentaService.buscarPorAdmin(decodedBanco, moneda);
		if (cuentaAdmin != null) {
			return ResponseEntity.ok(cuentaAdmin);
		}
		return ResponseEntity.notFound().build();
	}*/
	/*
	@GetMapping("/{id}/{moneda}")
	public ResponseEntity<CuentaBancariaUsuario> listarPorUsuarioMoneda(@PathVariable("id") Long id, @PathVariable("moneda") String moneda){
		CuentaBancariaUsuario cuenta = cuentaService.buscarPorUsuarioMoneda(id, moneda);
		if (cuenta != null) {
			return ResponseEntity.ok(cuenta);
		}
		return ResponseEntity.notFound().build();
	}*/

//
	
	
	//roody
	/*
	@GetMapping("/CuentaBancariaBuscar/{id}")
    public ResponseEntity<CuentaBancariaUsuario> obtenerCuentaBancariaId(@PathVariable Long id) {
        CuentaBancariaUsuario cuentaBancaria = cuentaService.findById(id);
        return ResponseEntity.ok(cuentaBancaria);
    }*/
	
	
	@GetMapping("/CuentaBancariaListaXIdCuentaUsuario/{idcuentaUsuario}")
	public ResponseEntity<?> CuentaBancarialista(@PathVariable Long idcuentaUsuario){
		
		
		//
		/*List<CuentaBancariaUsuario> cliente =  null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			 cliente = cuentaService.findbyCuentaUsuarioId(idcuentaUsuario);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		if(cliente == null) {
			response.put("mensaje", "La cuenta bancaria ID: " .concat(idcuentaUsuario.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CuentaBancariaUsuario>(cliente, HttpStatus.OK); */
		
		List<CuentaBancariaUsuario> cliente = cuentaService.findbyCuentaUsuarioId(idcuentaUsuario);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/CuentaBancariaBuscar/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		CuentaBancariaUsuario cliente =  null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			 cliente = cuentaService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		if(cliente == null) {
			response.put("mensaje", "La cuenta bancaria ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CuentaBancariaUsuario>(cliente, HttpStatus.OK); 
		
	}
	
	
	
	
	
	@PostMapping("/CuentaBancariaRegistrar")
	public ResponseEntity<?> create(@Validated  @RequestBody CuentaBancariaUsuario cuentaBancariaUsuario, BindingResult result)
	{
		CuentaBancariaUsuario cuentaBancariaUsuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		//roody comente 13_03_2023
		//CuentaUsuario s = new CuentaUsuario(); 
	 	//s.setId((long) 1);
		
	 	
		if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					 .stream()
					 .map(err -> "El campo '"+ err.getField() + "'" + err.getDefaultMessage())
					 .collect(Collectors.toList());
			
			response.put("errors",errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		try {
			
			//cuentaBancariaUsuario.setCuentaUsuario(s);
			//cuentaBancariaUsuario.setUsuario(s);

			//cuentaBancariaUsuario.setArchivo("");
		//	var ss =1;
			//s.setId(new setid);
		
			//.setUsuario();
			cuentaBancariaUsuarioNew = cuentaService.registrarCuenta(cuentaBancariaUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "La cuenta bancaria ha sido creado con éxito!");
		response.put("cliente", cuentaBancariaUsuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	
	
	//Roody
	@PutMapping("/CuentaBancariaActualizar/{id}")
	public ResponseEntity<?>  update(@Validated @RequestBody CuentaBancariaUsuario cuentaBancariaUsuario, BindingResult result,
			@PathVariable Long id)
	{
		CuentaBancariaUsuario CuentaBancariaUsuarioActual = cuentaService.findById(id);
		CuentaBancariaUsuario CuentaBancariaUsuarioUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
	if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					 .stream()
					 .map(err -> "El campo '"+ err.getField() + "'" + err.getDefaultMessage())
					 .collect(Collectors.toList());
			
			response.put("errors",errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		if(CuentaBancariaUsuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar La cuenta bancaria ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			CuentaBancariaUsuarioActual.setCci(cuentaBancariaUsuario.getCci());
			CuentaBancariaUsuarioActual.setNumerocuenta(cuentaBancariaUsuario.getNumerocuenta());
			
			CuentaBancariaUsuarioActual.setTipoCuentaBancaria(cuentaBancariaUsuario.getTipoCuentaBancaria());
			CuentaBancariaUsuarioActual.setMoneda(cuentaBancariaUsuario.getMoneda());
			//CuentaBancariaUsuarioActual.setBanco(cuentaBancariaUsuario.getBanco());ROODY COMENTE 2606/2023
			
			CuentaBancariaUsuarioUpdate = cuentaService.registrarCuenta(CuentaBancariaUsuarioActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		response.put("mensaje", "La cuenta bancaria ha sido actualizado con éxito!");
		response.put("cliente", CuentaBancariaUsuarioUpdate); 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 

	}
	
	
	
	@DeleteMapping("/CuentaBancariaEliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		CuentaBancariaUsuario cliente = cuentaService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if(cliente == null) {
			response.put("mensaje", "Error: no se pudo eliminar La cuenta bancaria ID: " .concat(id.toString().concat(" no existe en la base de datos")));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			// inicio:eliminar la foto cuando se elmina la cuneta bancaria
			CuentaBancariaUsuario clienteFoto = cuentaService.findById(id);
			
			String nombreFotoAnterior = clienteFoto.getArchivo();
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0)
			{
				Path rutaFotoAnterioir = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterioir.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			//fin: eliminar la foto cuando se elmina la cuneta bancaria

			cuentaService.eliminarCuenta(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La cuenta ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	
	
	
	//foto archivo desde formData
	@PostMapping("/CuentaBancariaFoto/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		CuentaBancariaUsuario cliente = cuentaService.findById(id);
		
		if(!archivo.isEmpty())
		{
			String nombreArchivo = UUID.randomUUID().toString()+ "_" +  archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchibo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchibo.toString());
			try {
				Files.copy(archivo.getInputStream(), rutaArchibo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente" + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
			
			String nombreFotoAnterior = cliente.getArchivo();
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0)
			{
				Path rutaFotoAnterioir = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterioir.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			cliente.setArchivo(nombreArchivo);
			cuentaService.registrarCuenta(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
 		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	//foto para ver ruta en navegador y descarga automaticamente
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto)
	{
		Path rutaArchibo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchibo.toString());
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchibo.toUri());
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable())
		{
			rutaArchibo = Paths.get("src/main/resources/static/images").resolve("no-img.png").toAbsolutePath();
			try {
				recurso = new UrlResource(rutaArchibo.toUri());
			} catch (MalformedURLException e) {
			
				e.printStackTrace();
			}
			
			log.error("Error no se pudo cargar la imagen: "+nombreFoto);
			
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()+ "\"" );
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//combo box
	@GetMapping("/bancos")
	public List<Banco> listarRegiones(){
		
		return cuentaService.findAllBancos();
	}
	
	@GetMapping("/monedas")
	public List<Moneda> listarmonedas(){
		
		return cuentaService.findAllMonedas();
	}
	
	@GetMapping("/tipoCuentaBancaria")
	public List<TipoCuentaBancaria> listarTipoCuentaBancaria(){
		
		return cuentaService.findAllTipoCuentaBancaria();
	}

	
}
