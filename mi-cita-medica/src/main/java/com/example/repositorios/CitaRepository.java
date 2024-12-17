package com.example.repositorios;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPacienteId(Integer pacienteId);
    List<Cita> findByMedicoIdAndFecha(Integer medicoId, Date fecha);
    boolean existsByFechaAndHoraAndMedicoId(Date fecha, Time hora, Integer medicoId);
    boolean existsByFechaAndHoraAndPacienteId(Date fecha, Time hora, Integer pacienteId);
    boolean existsByFechaAndHora(Date fecha, Time hora);
}