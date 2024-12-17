package com.example.servicios;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entidades.Cita;
import com.example.entidades.Estado;
import com.example.repositorios.CitaRepository;
import com.example.repositorios.EstadoRepository;

@Service
public class PacienteService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    // primer servicio para crear cita
    public Cita solicitarCita(Cita nuevaCita) {
        // verficacion de horario
        if (nuevaCita.getHora().before(Time.valueOf("07:00:00")) || 
            nuevaCita.getHora().after(Time.valueOf("12:00:00")) && 
            nuevaCita.getHora().before(Time.valueOf("14:00:00")) ||
            nuevaCita.getHora().after(Time.valueOf("18:00:00"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horario no permitido");
        }

        // verificacion de repetido
        if (citaRepository.existsByFechaAndHora(nuevaCita.getFecha(), nuevaCita.getHora())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una cita en el horario solicitado");
        }

        // verficacion de cita existente
        if (citaRepository.existsByFechaAndHoraAndPacienteId(nuevaCita.getFecha(), nuevaCita.getHora(), nuevaCita.getPaciente().getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una cita en el horario solicitado");
        }

        // estado inicial de la nueva cita
        Estado estadoPendiente = estadoRepository.findByNombre("Pendiente");
        nuevaCita.setEstado(estadoPendiente);

        return citaRepository.save(nuevaCita);
    }
}

