package com.example.entidades;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Cita> citasComoPaciente;

    @OneToMany(mappedBy = "medico")
    @JsonIgnore
    private Set<Cita> citasComoMedico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Set<Cita> getCitasComoPaciente() {
        return citasComoPaciente;
    }

    public void setCitasComoPaciente(Set<Cita> citasComoPaciente) {
        this.citasComoPaciente = citasComoPaciente;
    }

    public Set<Cita> getCitasComoMedico() {
        return citasComoMedico;
    }

    public void setCitasComoMedico(Set<Cita> citasComoMedico) {
        this.citasComoMedico = citasComoMedico;
    }    
}