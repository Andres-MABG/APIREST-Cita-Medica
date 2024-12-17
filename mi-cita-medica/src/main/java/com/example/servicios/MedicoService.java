package com.example.servicios;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.CitaDTO;
import com.example.entidades.Cita;
import com.example.entidades.Estado;
import com.example.entidades.Usuario;
import com.example.repositorios.CitaRepository;
import com.example.repositorios.EstadoRepository;
import com.example.repositorios.UsuarioRepository;

@Service
public class MedicoService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public CitaDTO confirmarCita(Integer citaId, Integer usuarioId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        // Validación de médico (igual que antes)
        Usuario medico = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        if (!medico.getRol().getNombre().equalsIgnoreCase("medico")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario no tiene permisos para confirmar la cita");
        }
        if (cita.getMedico() == null) {
            cita.setMedico(medico);
        } else if (!cita.getMedico().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para confirmar esta cita");
        }

        // Verificación de pagos
        if (cita.getPagos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita no ha sido pagada");
        }

        // Confirmar la cita
        Estado estadoConfirmado = estadoRepository.findByNombre("Confirmada");
        cita.setEstado(estadoConfirmado);
        citaRepository.save(cita);

        // Convertir a DTO
        return new CitaDTO(cita);
    }


    //servicio para el listado de citas
    public List<CitaDTO> listarCitasDelDia(Integer medicoId, Date fecha) {
        // Obtener la lista de citas
        List<Cita> citas = citaRepository.findByMedicoIdAndFecha(medicoId, fecha);

        // Convertir la lista de Cita a CitaDTO
        return citas.stream()
                    .map(CitaDTO::new) // Usar el constructor del DTO
                    .collect(Collectors.toList());
    }
}
