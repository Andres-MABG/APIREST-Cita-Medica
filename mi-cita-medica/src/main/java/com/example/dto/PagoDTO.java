package com.example.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PagoDTO {
    private Integer id;
    private BigDecimal monto;
    private Date fechaPago;
    private String metodoPago;
    private CitaDTO cita;
    private String mensajeError;

    public PagoDTO(Integer id, BigDecimal monto, Date fechaPago, String metodoPago, CitaDTO cita) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.cita = cita;
    }

    public PagoDTO(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    
}
