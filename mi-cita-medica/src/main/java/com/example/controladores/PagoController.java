package com.example.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.IniciarPagoDTO;
import com.example.dto.PagoDTO;
import com.example.servicios.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/iniciar")
    public ResponseEntity<String> iniciarPago(@RequestBody IniciarPagoDTO pagoDTO) {
        return ResponseEntity.ok(pagoService.iniciarPagoConPayPal(pagoDTO.getCitaId(), pagoDTO.getMonto()));
    }

    @GetMapping("/confirmar")
    public ResponseEntity<PagoDTO> confirmarPago(
            @RequestParam Integer citaId,
            @RequestParam String paymentId,
            @RequestParam("PayerID") String payerId) {
        return ResponseEntity.ok(pagoService.confirmarPagoConPayPal(citaId, paymentId, payerId));
    }

}
