package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Inversion;
import com.pi.factoring_backend.repository.CuentaUsuarioRepository;
import com.pi.factoring_backend.repository.InversionRepository;
import com.pi.factoring_backend.util.PageResponse;
import com.pi.factoring_backend.util.RecursoNoEncontradoException;

@Service
public class InversionServiceImpl implements InversionService {

	@Autowired
	private InversionRepository inversionRepository;
	
	@Autowired
	private CuentaUsuarioRepository cuentaUsuarioRepository;
	
	@Override
	public PageResponse<Inversion> listarxCuentaUsuario(Long idusuario, int page, int size) {
		CuentaUsuario cuentaUsuario = cuentaUsuarioRepository.findById(idusuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("La cuenta de usuario no existe"));
		
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Inversion> inversiones = inversionRepository.findInversionXUsuarioId(cuentaUsuario.getId(), pageable);
		
		PageResponse<Inversion> pagination = new PageResponse<Inversion>(inversiones);
		
		return pagination;
	}

	@Override
	public Inversion registrarInversion(Inversion inversion) {
		return inversionRepository.save(inversion);
	}

	@Override
	public List<Inversion> listarInversiones() {
		return inversionRepository.findAll();
	}

	// ROODY 18/06/2023
	/*@Override
	@Transactional(readOnly = true)
	public List<Inversion> findInversionXUsuarioId(Long id) {
		return inversionRepository.findInversionXUsuarioId(id);
	}*/
	
	//ROODY 18/06/2023
		@Override
		@Transactional(readOnly = true)
		public List<Inversion> findInversionXUsuarioId(Long id) {
			return inversionRepository.findInversionXUsuarioId(id);
		}
		
		@Override
		public List<Inversion> listaInversionesListaXIdCuentaUsuarioYXEstadoLike(Long idcuentaUsuario,  String estado) {
			return inversionRepository.listaInversionesListaXIdCuentaUsuarioYXEstadoLike(idcuentaUsuario, estado);
		}

}
