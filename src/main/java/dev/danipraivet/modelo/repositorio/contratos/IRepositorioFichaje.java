package dev.danipraivet.modelo.repositorio.contratos;

import dev.danipraivet.modelo.entidades.Fichaje;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Contrato de acceso a datos para fichajes
 * @author Daniel Rodríguez Pérez
 */
public interface IRepositorioFichaje {

    /**
     * Registra entrada o salida llamando al stored procedure de MySQL
     * @param codEmpleado valor numérico identificador del empleado
     * @return mensaje de depuración del fichaje
     */
    String registrarFichaje(int codEmpleado);

    /**
     * Obtiene el fichaje del día actual para un empleado
     *
     * @param codEmpleado código del empleado
     * @return un Optional con el fichaje si existe hoy
     */
    Optional<Fichaje> buscarFichajeHoy(int codEmpleado);

    /**
     * Busca todos los fichajes de un empleado en un rango de fechas
     *
     * @param codEmpleado código del empleado
     * @param desde fecha de inicio
     * @param hasta fecha de fin
     * @return lista de fichajes en el rango
     */
    List<Fichaje> buscarPorEmpleadoYRango(int codEmpleado, LocalDate desde, LocalDate hasta);

    /**
     * Busca todos los fichajes de una fecha concreta.
     *
     * @param fecha fecha a consultar
     * @return lista de fichajes de ese día
     */
    List<Fichaje> buscarPorFecha(LocalDate fecha);

    /**
     * Lista los fichajes con datos del empleado incluidos
     * @param desde fecha de inicio
     * @param hasta fecha de fin
     * @return lista de todos los empleados con fichaje entre esas fechas
     */
    List<Fichaje> listarTodosConEmpleado(LocalDate desde, LocalDate hasta);

    /**
     * Actualiza los datos de un fichaje
     *
     * @param fichaje fichaje con los datos modificados
     * @return {@code true} si la actualización fue exitosa
     */
    boolean actualizar(Fichaje fichaje);

    /**
     * Elimina un fichaje de forma permanente
     *
     * @param id identificador del fichaje
     * @return {@code true} si se eliminó correctamente
     */
    boolean eliminar(int id);

    /**
     * Comprueba si el empleado tiene un fichaje abierto hoy (sin salida registrada)
     * @param codEmpleado valor numérico identificador del empleado
     * @return si el empleado ha fichado hoy o no
     */
    boolean estaFichadoHoy(int codEmpleado);

    /**
     * @return número de empleados que han fichado la entrada pero no la salida hoy
     */
    int contarFichadosHoy();
}
