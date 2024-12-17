package com.example.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RolPermisoId implements Serializable {
    @Column(name = "rol_id")
    private Integer rolId;
    
    @Column(name = "permiso_id")
    private Integer permisoId;

    public RolPermisoId() {}

    public RolPermisoId(Integer rolId, Integer permisoId) {
        this.rolId = rolId;
        this.permisoId = permisoId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Integer getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(Integer permisoId) {
        this.permisoId = permisoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolPermisoId that = (RolPermisoId) o;
        return rolId.equals(that.rolId) && permisoId.equals(that.permisoId);
    }

    @Override
    public int hashCode() {
        return 31 * rolId.hashCode() + permisoId.hashCode();
    }
}
