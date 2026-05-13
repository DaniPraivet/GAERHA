package dev.danipraivet.modelo.enumeraciones;

import lombok.Getter;

/**
 * Roles del sistema
 * @author Daniel Rodríguez Pérez
 */
@Getter
public enum Rol {

    EMPLEADO("Empleado"), RRHH("Recursos Humanos"), ADMIN("Administrador");

    private final String etiqueta;

    Rol(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Convierte el String de MySQL al enum, devuelve EMPLEADO si el valor es nulo o desconocido
     * @param valor contenido a transformar
     * @return se devuelve el valor transformado
     */
    public static Rol fromString(String valor) {
        if (valor == null) return EMPLEADO;
        return switch (valor.toUpperCase()) {
            case "RRHH" -> RRHH;
            case "ADMIN" -> ADMIN;
            default -> EMPLEADO;
        };
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
