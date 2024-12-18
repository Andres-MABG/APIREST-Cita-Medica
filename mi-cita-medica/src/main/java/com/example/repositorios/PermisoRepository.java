package com.example.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
}
