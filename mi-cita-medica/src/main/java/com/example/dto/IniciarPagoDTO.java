package com.example.dto;

import java.math.BigDecimal;

public class IniciarPagoDTO {
    private Integer citaId;
    private BigDecimal monto;

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}