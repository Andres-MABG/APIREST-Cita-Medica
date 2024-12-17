package com.example.dto;

import com.example.entidades.Cita;

public class CitaDTO {
    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private String fecha;
    private String hora;
    private String especialidad;
    private String estado;
    private String mensajeError;

    public CitaDTO(Cita cita) {
        this.id = cita.getId();
        this.pacienteId = cita.getPaciente().getId();
        this.pacienteNombre = cita.getPaciente().getNombre();
        this.fecha = cita.getFecha().toString();
        this.hora = cita.getHora().toString();
        this.especialidad = cita.getEspecialidad();
        this.estado = cita.getEstado().getNombre();
        this.mensajeError = null;
    }

    public CitaDTO(Integer id, Integer pacienteId, String pacienteNombre, String fecha, String hora, String especialidad, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.fecha = fecha;
        this.hora = hora;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public CitaDTO(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    
}

