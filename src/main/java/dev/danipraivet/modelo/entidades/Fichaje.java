package dev.danipraivet.modelo.entidades;

import dev.danipraivet.modelo.enumeraciones.Turno;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Entidad que representa un registro de fichaje diario
 * @author Daniel Rodríguez Pérez
 */
@Getter
@Setter
public class Fichaje {

    private int id;
    private LocalDate fecha;
    private int codEmpleado;

    private Empleado empleado;

    private LocalTime entradaHora;
    private LocalTime salidaHora;
    private Turno turnoEntrada;
    private Turno turnoSalida;

    private BigDecimal horasTrabajadas;
    private BigDecimal horasExtras;

    private boolean festivo;
    private boolean justificado;
    private String observaciones;

    private LocalDateTime creadoEn;
    private LocalDateTime modificadoEn;

    /**
     * Constructor principal
     */
    public Fichaje() {
        this.horasTrabajadas = BigDecimal.ZERO;
        this.horasExtras = BigDecimal.ZERO;
    }

    /**
     * Constructor secundario
     * @param codEmpleado código numérico para el empleado
     * @param fecha dia, mes y año del fichaje
     * @param entradaHora hora de entrada del fichaje
     * @param turnoEntrada hora de salida del fichaje
     */
    public Fichaje(int codEmpleado, LocalDate fecha, LocalTime entradaHora, Turno turnoEntrada) {
        this();
        this.codEmpleado = codEmpleado;
        this.fecha = fecha;
        this.entradaHora = entradaHora;
        this.turnoEntrada = turnoEntrada;
    }

    /**
     * Indica si el fichaje ya tiene registrada la salida
     *
     * @return {@code true} si la hora de salida no es {@code null}
     */
    public boolean estaCompleto() {
        return salidaHora != null;
    }

    /**
     * Indica si el empleado ha fichado la entrada pero todavía no la salida
     *
     * @return {@code true} si hay entrada y no hay salida
     */
    public boolean estaFichado() {
        return entradaHora != null && salidaHora == null;
    }

    /**
     * Formatea las horas trabajadas como cadena legible
     * Si no hay horas trabajadas devuelve cadena vacía.
     *
     * @return cadena de texto formateada
     */
    public String getHorasFormateadas() {
        if (horasTrabajadas == null || horasTrabajadas.compareTo(BigDecimal.ZERO) == 0) {
            return "";
        }
        int horas = horasTrabajadas.intValue();
        int minutos = (int) Math.round((horasTrabajadas.doubleValue() - horas) * 60);
        return horas + "h " + String.format("%02d", minutos) + "m";
    }

    /**
     * Devuelve el estado actual del fichaje
     * @return cadena del estado
     */
    public String getEstado() {
        if (entradaHora == null) return "Ausente";
        if (salidaHora == null) return "Trabajando";
        return "Completado";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fichaje f)) return false;
        return id == f.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fichaje{id=" + id + ", codEmpleado=" + codEmpleado + ", fecha=" + fecha + ", entrada=" + entradaHora + ", salida=" + salidaHora + ", horas=" + horasTrabajadas + ", estado='" + getEstado() + "'}";
    }
}
