package com.pi.factoring_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Document(value = "bancomongo")
@Data
public class Bancomongo {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;

	//private String id;
}

