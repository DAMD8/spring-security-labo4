package com.server.app.entities.impl;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String tipo;

    @Column(name = "categoria_padre_id")
    private Integer categoriaPadreId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getCategoriaPadreId() { return categoriaPadreId; }
    public void setCategoriaPadreId(Integer categoriaPadreId) { this.categoriaPadreId = categoriaPadreId; }
}