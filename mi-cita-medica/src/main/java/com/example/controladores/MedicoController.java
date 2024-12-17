package com.example.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CitaDTO;
import com.example.dto.ConfirmarCitaDTO;
import com.example.servicios.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/citas/confirmar")
    public ResponseEntity<CitaDTO> confirmarCita(@RequestBody ConfirmarCitaDTO confirmacion) {
        CitaDTO citaConfirmada = medicoService.confirmarCita(confirmacion.getCitaId(), confirmacion.getMedicoId());
        return ResponseEntity.ok(citaConfirmada);
    }

    @GetMapping("/citas")
    public ResponseEntity<List<CitaDTO>> listarCitasDelDia1(@RequestParam Integer medicoId,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ResponseEntity.ok(medicoService.listarCitasDelDia(medicoId, fecha));
    }
}
