package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_producto")
@Getter @Setter @NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "pr_nombre", nullable = false)
    private String nombre;

    @Column(name = "pr_precio")
    private BigDecimal precio;

    @Column(name = "pr_activo")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor; 

    @ManyToMany
    @JoinTable(name = "producto_distribuidor",
               joinColumns = @JoinColumn(name = "producto_id"),
               inverseJoinColumns = @JoinColumn(name = "distribuidor_id"))
    private List<Distribuidor> distribuidores; 

    @OneToMany(mappedBy = "producto")
    private List<Mantenimiento> mantenimientos; 

    // ¡ESTE ES EL CONSTRUCTOR MÁGICO QUE FALTA!
    public Producto(String nombre, BigDecimal precio, Categoria categoria, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }
}
