package com.proyecto.catalogo.controller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.catalogo.modelo.Producto;
import com.proyecto.catalogo.service.ServiceProducto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ServiceProducto serviceProducto;

    public ProductoController(ServiceProducto serviceProducto) {
        this.serviceProducto = serviceProducto;
    }

    //Obtener Todo
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosProductos() {
        return new ResponseEntity<>(serviceProducto.obtenerTodosLosProductos(),HttpStatus.OK);
    }

    //Por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoId(@PathVariable Long id){
        Optional<Producto> prod = serviceProducto.obtenerProductoPorID(id);
        if (prod.isPresent()){
            return new ResponseEntity<>(prod,HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //Agregar productos
    @PostMapping("/auto")
    public ResponseEntity<Producto> agregarProducto(@Valid @RequestBody Producto producto){
        Producto prod = serviceProducto.CrearProducto(producto);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @PostMapping("/manual")
    public ResponseEntity<?> agregarProductoManual(@RequestBody Producto producto){
        List<String> error = serviceProducto.ValidacionManualProducto(producto);
        if (error.isEmpty()) {
            Producto prod = serviceProducto.CrearProducto(producto);
            return new ResponseEntity<>(prod, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(error);
        }
    }
    //Borrar Producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id){
        if (serviceProducto.eliminarProducto(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Actualizar Producto
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
            if (serviceProducto.actualizarProducto(id, producto)!=null){
                return new ResponseEntity<>(producto,HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
    }
}
