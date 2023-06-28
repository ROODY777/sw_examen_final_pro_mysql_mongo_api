package com.pi.factoring_backend.service;

import java.util.List;

import com.pi.factoring_backend.entity.Inversion;
import com.pi.factoring_backend.util.PageResponse;

public interface InversionService {
	PageResponse<Inversion> listarxCuentaUsuario(Long idusuario, int page, int size);
	
	public Inversion registrarInversion(Inversion inversion);
	
	public List<Inversion> listarInversiones();
	
	//roody agregue 18/06/2023
	public List<Inversion> findInversionXUsuarioId(Long id);
	

	public abstract List<Inversion> listaInversionesListaXIdCuentaUsuarioYXEstadoLike(Long idcuentaUsuario,  String estado);
}
