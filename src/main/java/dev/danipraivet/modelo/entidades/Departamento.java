package dev.danipraivet.modelo.entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa un departamento
 * @author Daniel Rodríguez Pérez
 */
@Setter
@Getter
public class Departamento {

    private int codDepartamento;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    /**
     * Constructor vacío requerido por algunas operaciones de mapeo
     */
    public Departamento() {
    }
    /**
     * Crea un departamento activo con el nombre y la descripción indicados.
     *
     * @param nombre nombre del departamento
     * @param descripcion descripción breve de sus funciones
     */
    public Departamento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }
    /**
     * Constructor completo.
     *
     * @param codDepartamento identificador único
     * @param nombre nombre del departamento
     * @param descripcion descripción
     * @param activo {@code true} si el departamento está activo
     * @param fechaCreacion fecha y hora de creación
     */
    public Departamento(int codDepartamento, String nombre, String descripcion, boolean activo, LocalDateTime fechaCreacion) {
        this.codDepartamento = codDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento d)) return false;
        return codDepartamento == d.codDepartamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDepartamento);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
