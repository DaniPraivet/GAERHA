package dev.danipraivet.modelo.enumeraciones;

import lombok.Getter;
/**
 * Turnos laborales
 * @author Daniel Rodríguez Pérez
 */
@Getter
public enum Turno {

    MANANA("Mañana", 6, 13), TARDE("Tarde", 14, 21), NOCHE("Noche", 22, 5);

    private final String etiqueta;
    private final int horaInicio;
    private final int horaFin;

    Turno(String etiqueta, int horaInicio, int horaFin) {
        this.etiqueta = etiqueta;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     * Detecta el turno según la hora actual
     * @return el turno detectado
     */
    public static Turno detectar() {
        int hora = java.time.LocalTime.now().getHour();
        if (hora >= 6 && hora <= 13) return MANANA;
        if (hora >= 14 && hora <= 21) return TARDE;
        return NOCHE;
    }

    /**
     * Convierte el String de MySQL al enum
     * @param valor objeto a transformar
     * @return objeto ya transformado
     */
    public static Turno fromString(String valor) {
        if (valor == null) return null;
        return switch (valor) {
            case "Mañana" -> MANANA;
            case "Tarde" -> TARDE;
            case "Noche" -> NOCHE;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
