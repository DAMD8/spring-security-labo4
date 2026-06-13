package com.server.app.entities.impl;

import jakarta.persistence.*;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;
    private String moneda;

    @Column(name = "saldo_base")
    private Double saldoBase;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }
    public Double getSaldoBase() { return saldoBase; }
    public void setSaldoBase(Double saldoBase) { this.saldoBase = saldoBase; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }
}