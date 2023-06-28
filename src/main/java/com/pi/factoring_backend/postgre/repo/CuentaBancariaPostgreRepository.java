package com.pi.factoring_backend.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pi.factoring_backend.postgre.entity.CuentaBancariaPostgre;

public interface CuentaBancariaPostgreRepository  extends JpaRepository<CuentaBancariaPostgre, Long>{

}
