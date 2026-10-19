package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_producto")
@Getter @Setter @NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pr_id", nullable = false)
    private Integer id;

    @Column(name = "pr_nombre", nullable = false)
    private String nombre;

    @Column(name = "pr_cantidad")
    private Integer cantidad;

    @Column(name = "pr_precio")
    private Double precio;

    public Producto(String nombre, Integer cantidad, Double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}