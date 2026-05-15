package com.proyecto.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.catalogo.service.ServiceProducto;
import com.proyecto.catalogo.dto.*;

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
        return new ResponseEntity<>(serviceProducto.findAllDTO(),HttpStatus.OK);
    }

    //Por ID
    //@GetMapping("/regular/{id}")
    //public ResponseEntity<?> obtenerProductoId(@PathVariable Long id){
    //    Optional<Producto> prod = serviceProducto.obtenerProductoPorID(id);
    //    if (prod.isPresent()){
    //        return new ResponseEntity<>(prod,HttpStatus.OK);
    //    } else {
    //        return ResponseEntity.badRequest().build();
    //    }
    //}

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoDTOId(@PathVariable Long id){
        ProductoDTO prod = serviceProducto.findDtoById(id);
        if (prod != null){
            return new ResponseEntity<>(prod,HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //Agregar productos
    @PostMapping("/auto")
    public ResponseEntity<ProductoDTO> agregarProducto(@Valid @RequestBody ProductoCreateDTO producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceProducto.crearProductoDTO(producto));
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
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody ProductoCreateDTO dto){
            if (serviceProducto.ActualizarProductoDTO(id, dto)!=null){
                return new ResponseEntity<>(dto,HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
    }
}
