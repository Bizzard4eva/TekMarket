package com.pruebatecnica.TecMarket.repository;

import com.pruebatecnica.TecMarket.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> { }
