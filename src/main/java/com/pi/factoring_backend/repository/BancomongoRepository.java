package com.pi.factoring_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.pi.factoring_backend.entity.Bancomongo;

public interface BancomongoRepository extends MongoRepository<Bancomongo,Long> {

}
