package com.example.servicios;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.CitaDTO;
import com.example.dto.PagoDTO;
import com.example.entidades.Cita;
import com.example.entidades.Estado;
import com.example.entidades.Pago;
import com.example.repositorios.CitaRepository;
import com.example.repositorios.EstadoRepository;
import com.example.repositorios.PagoRepository;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaypalService payPalService;

    public String iniciarPagoConPayPal(Integer citaId, BigDecimal monto) {
        // verificacion de cita existente
        Cita cita = citaRepository.findById(citaId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        // verificacion de pago existente
        if (pagoRepository.existsByCitaId(citaId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita ya ha sido pagada");
        }

        // Crear el pago en PayPal
        try {
            Payment payment = payPalService.crearPago(
                monto,
                "USD",
                "paypal",
                "Pago de cita médica",
                "http://localhost:8080/api/pagos/confirmar?citaId=" + citaId,
                "http://localhost:8080/api/pagos/cancelar"
            );

            return payment.getLinks().get(1).getHref(); // se retorna la URL para la redirección
        } catch (PayPalRESTException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar el pago con PayPal");
        }
    }

    public PagoDTO confirmarPagoConPayPal(Integer citaId, String paymentId, String payerId) {
        try {
            // confirma el pago en PayPal
            Payment payment = payPalService.ejecutarPago(paymentId, payerId);

            Transaction transaction = payment.getTransactions().get(0);
            Amount amount = transaction.getAmount();
            BigDecimal montoPagado = new BigDecimal(amount.getTotal());

            Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

            Estado estadoPagada = estadoRepository.findByNombre("pagada");
            cita.setEstado(estadoPagada);
            citaRepository.save(cita);

            // creacion del pago
            Pago nuevoPago = new Pago();
            nuevoPago.setCita(cita);
            nuevoPago.setMonto(montoPagado); 
            nuevoPago.setMetodoPago("PayPal");
            nuevoPago.setFechaPago(new Date());

            pagoRepository.save(nuevoPago);

            // mapeo dto
            return mapToPagoDTO(nuevoPago);

        } catch (PayPalRESTException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al confirmar el pago con PayPal");
        }
    }

    // mapea los datos generados al dto para el response limpio
    private PagoDTO mapToPagoDTO(Pago pago) {
        Cita cita = pago.getCita();

        Integer pacienteId = (cita.getPaciente() != null) ? cita.getPaciente().getId() : null;
        String pacienteNombre = (cita.getPaciente() != null) ? cita.getPaciente().getNombre() : "Desconocido";
        String estadoNombre = (cita.getEstado() != null) ? cita.getEstado().getNombre() : "No asignado";

        CitaDTO citaDTO = new CitaDTO(
            cita.getId(),
            pacienteId,
            pacienteNombre,
            cita.getFecha().toString(),
            cita.getHora().toString(),
            cita.getEspecialidad(),
            estadoNombre
        );

        return new PagoDTO(
            pago.getId(),
            pago.getMonto(),
            pago.getFechaPago(),
            pago.getMetodoPago(),
            citaDTO
        );
    }    
}


