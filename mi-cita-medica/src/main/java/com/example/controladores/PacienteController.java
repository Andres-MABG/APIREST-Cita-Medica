package com.example.controladores;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CitaDTO;
import com.example.dto.SolicitarCitaDTO;
import com.example.entidades.Cita;
import com.example.entidades.Usuario;
import com.example.repositorios.UsuarioRepository;
import com.example.servicios.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/citas")
    public ResponseEntity<CitaDTO> solicitarCita(@RequestBody SolicitarCitaDTO solicitud) {

        Cita nuevaCita = new Cita();

        Usuario usuario = usuarioRepository.findById(solicitud.getPacienteId())
            .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        nuevaCita.setPaciente(usuario);
        nuevaCita.setFecha(Date.valueOf(solicitud.getFecha()));
        nuevaCita.setHora(Time.valueOf(solicitud.getHora() + ":00"));
        nuevaCita.setEspecialidad(solicitud.getEspecialidad());

        Cita citaGuardada = pacienteService.solicitarCita(nuevaCita);

        return ResponseEntity.ok(new CitaDTO(citaGuardada));
    }
}
