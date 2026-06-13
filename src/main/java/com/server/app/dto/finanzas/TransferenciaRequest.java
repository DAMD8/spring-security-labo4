package com.server.app.dto.finanzas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaRequest {
    @NotNull(message = "La cuenta de origen es requerida")
    private Integer cuentaOrigenId;

    @NotNull(message = "La cuenta de destino es requerida")
    private Integer cuentaDestinoId;

    @NotNull(message = "El monto es requerido")
    @Positive(message = "El monto debe ser un valor positivo")
    private Double monto;

    @NotNull(message = "La tasa de cambio es requerida")
    @Positive(message = "La tasa de cambio debe ser un valor positivo")
    private Double tasaCambio;

    public Integer getCuentaOrigenId() { return cuentaOrigenId; }
    public void setCuentaOrigenId(Integer cuentaOrigenId) { this.cuentaOrigenId = cuentaOrigenId; }
    public Integer getCuentaDestinoId() { return cuentaDestinoId; }
    public void setCuentaDestinoId(Integer cuentaDestinoId) { this.cuentaDestinoId = cuentaDestinoId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public Double getTasaCambio() { return tasaCambio; }
    public void setTasaCambio(Double tasaCambio) { this.tasaCambio = tasaCambio; }
}