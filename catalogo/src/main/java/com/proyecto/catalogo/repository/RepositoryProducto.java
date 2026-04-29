package com.proyecto.catalogo.repository;

import org.springframework.stereotype.Repository;
import com.proyecto.catalogo.modelo.*;

//import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;


@Repository
public interface RepositoryProducto extends JpaRepository<Producto, Long> {
}