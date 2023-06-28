package com.pi.factoring_backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.factoring_backend.entity.Cartera;
import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.entity.TipoPerfilUsuario;
import com.pi.factoring_backend.security.JwtAuthResponse;
import com.pi.factoring_backend.security.JwtTokenProvider;
import com.pi.factoring_backend.service.CarteraService;
import com.pi.factoring_backend.service.CuentaUsuarioService;
import com.pi.factoring_backend.util.RecursoNoEncontradoException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaUsuarioController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CuentaUsuarioService usuarioService;
	
	@Autowired
	private CarteraService carteraService;
	
	@GetMapping
	public List<CuentaUsuario> listarUsuarios(){
		return usuarioService.listaUsuarios();
	}
	
	/*
	@PostMapping
	public CuentaUsuario registrarUsuario(@RequestBody CuentaUsuario usuario) {
		return usuarioService.registrarUsuario(usuario);
	}
	*/

	@GetMapping("/{id}")
	public CuentaUsuario obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioService.obtenerUsuarioPorId(id);
	}
	
	
	
	@PutMapping("/{id}")
	public CuentaUsuario actualizarUsuario(@RequestBody CuentaUsuario nuevoUsuario, @PathVariable Long id) {
		CuentaUsuario usuario = usuarioService.obtenerUsuarioPorId(id);
		usuario.setNombres(nuevoUsuario.getNombres());
		usuario.setApellidoPaterno(nuevoUsuario.getApellidoPaterno());
		usuario.setApellidoMaterno(nuevoUsuario.getApellidoMaterno());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setDireccion(nuevoUsuario.getDireccion());	
		usuario.setTelefono(nuevoUsuario.getTelefono());
		usuario.setRazonSocial(nuevoUsuario.getRazonSocial());
		usuario.setActividadcomercial(nuevoUsuario.getActividadcomercial());
		usuario.setRuc(nuevoUsuario.getRuc());
		usuario.setDescripcion(nuevoUsuario.getDescripcion());
		return usuarioService.actualizarUsuario(usuario);  
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id){
		CuentaUsuario usuario = usuarioService.obtenerUsuarioPorId(id);
		
		usuarioService.eliminarUsuario(usuario.getId());
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}			


	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody CuentaUsuario usuario){
	    Authentication auth = authenticationManager
	            .authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getContrasena()));
	    
	    SecurityContextHolder.getContext().setAuthentication(auth);
	    
	    //Obtenemos el token del jwtTokenProvider
	    String token = jwtTokenProvider.generarToken(auth);
	    
	    return ResponseEntity.ok(new JwtAuthResponse(token));
	}
	
	
	@GetMapping("/correo/{filtro}")
	@ResponseBody
	public ResponseEntity<List<CuentaUsuario>> listarUsuarioPorCorreo(@PathVariable("filtro") String filtro) {
	    List<CuentaUsuario> listar = usuarioService.listarUsuarioPorCorreo(filtro);
	    if (listar == null || listar.isEmpty()) {
	        throw new RecursoNoEncontradoException("No se encontraron usuarios con el correo " + filtro);
	    }
	    return ResponseEntity.ok(listar);
	}
	
	/*
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody CuentaUsuario usuario) {
	    CuentaUsuario usuarioLogeado = usuarioService.login(usuario.getEmail(),usuario.getContrasena());

	    if (usuarioLogeado != null && usuarioLogeado.getContrasena().equals(usuario.getContrasena())) {
	        return ResponseEntity.ok(usuarioLogeado);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	} */
	

	// Encriptar contraseñas
	@GetMapping("/bcrypt/{texto}")
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}
	
	//REGISTRAR USUARIO
	@PostMapping
	@Transactional
    public ResponseEntity<?> insertaUsuario(@RequestBody CuentaUsuario obj) {
        Map<String, Object> salida = new HashMap<>();
        List<String> lstMensajes = new ArrayList<>();
        salida.put("errores", lstMensajes);
        
        // Validar si el correo electrónico ya existe en la base de datos
        CuentaUsuario cuentaExistente = usuarioService.buscarCuentaUsuarioPorCorreo(obj.getEmail());
        List<CuentaUsuario> cuentaExistenteporRuc = usuarioService.listarUsuarioPorRuc(obj.getRuc());
        
        if (cuentaExistente != null && !cuentaExistenteporRuc.isEmpty()) {
            lstMensajes.add("Ya existe un usuario registrado con este correo electrónico");
            lstMensajes.add("Ya existe un usuario registrado con este Ruc");
            return ResponseEntity.badRequest().body(salida);
        }
        
        if (cuentaExistente != null) {
            lstMensajes.add("Ya existe un usuario registrado con este correo electrónico");
            return ResponseEntity.badRequest().body(salida);
        }
        if(obj.getRuc()!="") {
	        if(!cuentaExistenteporRuc.isEmpty()) {
	        	lstMensajes.add("Ya existe un usuario registrado con este Ruc");
	            return ResponseEntity.badRequest().body(salida);
	        }
        }

        Set<TipoPerfilUsuario> tiposPerfilesUsuario = new HashSet<>();
        for (TipoPerfilUsuario tipo : obj.getTiposPerfilesUsuario()) {
            TipoPerfilUsuario tipoPerfilUsuario = usuarioService.buscarTipoPerfilUsuarioPorId(tipo.getId());
            if (tipoPerfilUsuario == null) {
                lstMensajes.add("El tipo de perfil de usuario no existe");
                return ResponseEntity.badRequest().body(salida);
            }
            tiposPerfilesUsuario.add(tipoPerfilUsuario);
        }
        obj.setTiposPerfilesUsuario(tiposPerfilesUsuario);
        obj.setContrasena(passwordEncoder.encode(obj.getContrasena()));
        CuentaUsuario objSalida = usuarioService.registrarUsuario(obj);
        if (objSalida == null) {
            lstMensajes.add("Error en el registro");
        } else {
        	List<Moneda> monedas = carteraService.obtenerMonedas();
        	for (Moneda moneda : monedas) {
        		Cartera cartera = new Cartera();
        		cartera.setCuentaUsuario(objSalida);
        		cartera.setMoneda(moneda);
        		carteraService.registrarCartera(cartera);
        	}
            lstMensajes.add("Se registró Usuario con ID ==> " + objSalida.getId());
            
        }
        
        return ResponseEntity.ok(salida);
    }
	
	@GetMapping("/cartera/{id}")
	public ResponseEntity<List<Cartera>> listaCarteras(@PathVariable Long id){
		List<Cartera> lista = carteraService.obtenerPorUsuario(id);
		if (!lista.isEmpty()) {
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/cartera")
	public ResponseEntity<Cartera> actualizarCartera(@RequestBody Cartera nuevaCartera){
		Cartera cartera = carteraService.obtenerPorId(nuevaCartera.getCuentaUsuario().getId(), nuevaCartera.getMoneda().getDescripcion());
		cartera.setCantidad(nuevaCartera.getCantidad());
		carteraService.actualizarCantidad(cartera);
		return ResponseEntity.ok(cartera);
	}
	
	@GetMapping("/cartera/{id}/{moneda}")
	public ResponseEntity<Cartera> buscarPorId(@PathVariable("id") Long id, @PathVariable("moneda") String moneda){
		Cartera cartera = carteraService.obtenerPorId(id, moneda);
		if (cartera != null) {
			return ResponseEntity.ok(cartera);
		}
		return ResponseEntity.notFound().build();
	}
    
}


