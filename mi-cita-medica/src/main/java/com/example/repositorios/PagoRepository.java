package com.example.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByCitaId(Integer citaId);
    boolean existsByCitaId(Integer citaId);
}