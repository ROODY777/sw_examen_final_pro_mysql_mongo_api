package com.pi.factoring_backend.security;

public class JwtAuthResponse {
	private String tokenAcceso;
	private String tokeTipo = "Bearer";
	
	public JwtAuthResponse(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}

	public String getTokenAcceso() {
		return tokenAcceso;
	}

	public void setTokenAcceso(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}

	public String getTokeTipo() {
		return tokeTipo;
	}

	public void setTokeTipo(String tokeTipo) {
		this.tokeTipo = tokeTipo;
	}
	
}
