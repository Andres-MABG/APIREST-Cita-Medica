package com.example.dto;

import java.util.Date;

public class ListarCitasDTO {
    private Integer medicoId;
    private Date fecha;

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }    
}
