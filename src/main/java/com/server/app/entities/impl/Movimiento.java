package com.server.app.entities.impl;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double monto;

    @Column(name = "moneda_original")
    private String monedaOriginal;

    @Column(name = "tasa_cambio")
    private Double tasaCambio;

    private LocalDateTime fecha;
    private String description;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getMonedaOriginal() { return monedaOriginal; }
    public void setMonedaOriginal(String monedaOriginal) { this.monedaOriginal = monedaOriginal; }
    public Double getTasaCambio() { return tasaCambio; }
    public void setTasaCambio(Double tasaCambio) { this.tasaCambio = tasaCambio; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}