package com.example.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {
    List<RolPermiso> findByRolId(Integer rolId);
}