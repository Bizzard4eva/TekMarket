package com.pruebatecnica.TecMarket.repository;

import com.pruebatecnica.TecMarket.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Long> { }
