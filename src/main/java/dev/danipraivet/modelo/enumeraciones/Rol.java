package dev.danipraivet.modelo.enumeraciones;

/**
 * Roles del sistema
 * @author Daniel Rodríguez Pérez
 */
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

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
