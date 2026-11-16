package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_categoria")
@Getter @Setter @NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descripcion;

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }
}
