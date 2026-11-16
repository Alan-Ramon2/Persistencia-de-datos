package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_mantenimiento")
@Getter @Setter @NoArgsConstructor
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // ESTE ES EL CONSTRUCTOR QUE JAVA NECESITA
    public Mantenimiento(String descripcion, LocalDate fecha, Producto producto) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.producto = producto;
    }
}
