package com.proyecto.catalogo.controller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.catalogo.modelo.Carta;
import com.proyecto.catalogo.modelo.Producto;
import com.proyecto.catalogo.service.ServiceCarta;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos/carta")
public class CartaController {
    private final ServiceCarta serviceCarta;

    public CartaController(ServiceCarta serviceCarta) {
        this.serviceCarta = serviceCarta;
    }

    @PostMapping("/auto")
    public ResponseEntity<Carta> agregarCarta(@Valid @RequestBody Carta carta){
        Carta card = (Carta) serviceCarta.CrearCarta(carta);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PostMapping("/manual")
    public ResponseEntity<?> agregarCartaManual(@RequestBody Carta carta){
        List<String> error = serviceCarta.ValidacionManualCarta(carta);
        if (error.isEmpty()) {
            Carta card = (Carta) serviceCarta.CrearCarta(carta);
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(error);
        }
    }
    //Obtener Todo
    @GetMapping
    public ResponseEntity<?> obtenerTodasLasCartas() {
        return new ResponseEntity<>(serviceCarta.obtenerTodasLasCartas(),HttpStatus.OK);
    }

    //Por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCartaId(@PathVariable Long id){
        Optional<Producto> carta = serviceCarta.obtenerCartaPorID(id);
        if (carta.isPresent()){
            Carta cartaEncontrada = (Carta) carta.get();
            return new ResponseEntity<>(cartaEncontrada,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //Agregar Carta


    //Borrar Carta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCarta(@PathVariable Long id){
        if (serviceCarta.eliminarCarta(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Actualizar Carta
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCarta(@PathVariable Long id, @RequestBody Carta carta){
            if (serviceCarta.actualizarCarta(id, carta)!=null){
                return new ResponseEntity<>(carta,HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
    }
}
