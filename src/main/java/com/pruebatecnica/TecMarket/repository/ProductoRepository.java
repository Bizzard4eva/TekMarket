package com.pruebatecnica.TecMarket.repository;

import com.pruebatecnica.TecMarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> { }
