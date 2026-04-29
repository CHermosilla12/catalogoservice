package com.proyecto.catalogo.modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Carta extends Producto {

    @NotBlank(message = "El tipo de carta es obligatorio")
    private String tipo; // Tipo de carta (por ejemplo, "Carta de Pokémon", "Carta de Yu-Gi-Oh!", etc.)

    @NotBlank(message = "La rareza de la carta es obligatoria")
    private String rareza; // Rareza de la carta (por ejemplo, "Común", "Rara", "Ultra Rara", etc.)

    @NotBlank(message = "El idioma de la carta es obligatorio")
    private String idioma; // Idioma de la carta (por ejemplo, "Español", "Inglés", etc.)


}
