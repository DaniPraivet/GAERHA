package dev.danipraivet.modelo.servicio;

import dev.danipraivet.modelo.entidades.Fichaje;
import dev.danipraivet.modelo.enumeraciones.Rol;
import dev.danipraivet.modelo.repositorio.contratos.IRepositorioFichaje;
import dev.danipraivet.modelo.repositorio.implementacion.RepositorioFichaje;
import dev.danipraivet.modelo.utilidades.GestorSesion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Usa el rol de la sesion para conectar con los permisos correctos
 * @author Daniel Rodríguez Pérez
 */
public class ServicioFichaje {

    private static final Logger log = LoggerFactory.getLogger(ServicioFichaje.class);

    private final IRepositorioFichaje repo;

    public ServicioFichaje() {
        Rol rol = GestorSesion.haySesionActiva() ? GestorSesion.getRol() : Rol.EMPLEADO;
        this.repo = new RepositorioFichaje(rol);
    }

    /**
     * Registra la entrada o salida del empleado en sesión. Devuelve el mensaje del stored procedure
     * @return devuelve una cadena del texto con el mensaje
     */
    public String fichar() {
        int cod = GestorSesion.getCodEmpleado();
        String mensaje = repo.registrarFichaje(cod);
        log.info("Fichaje - empleado {} - {}", cod, mensaje);
        return mensaje;
    }

    /**
     * Registra el fichaje de un empleado específico (uso de Admin/RRHH)
     * @param codEmpleado valor numérico identificador del empleado
     * @return una cadena de texto con el resultado
     */
    public String ficharPor(int codEmpleado) {
        return repo.registrarFichaje(codEmpleado);
    }

    /**
     * Indica si el empleado en sesión tiene una entrada abierta hoy.
     *
     * @return {@code true} si ya fichó la entrada pero no la salida
     */
    public boolean estaFichadoHoy() {
        return repo.estaFichadoHoy(GestorSesion.getCodEmpleado());
    }

    /**
     * Obtiene el fichaje de hoy del empleado en sesión.
     *
     * @return un Optional con el fichaje si existe
     */
    public Optional<Fichaje> getFichajeHoy() {
        return repo.buscarFichajeHoy(GestorSesion.getCodEmpleado());
    }

    /**
     * Obtiene el fichaje de hoy de un empleado específico.
     *
     * @param codEmpleado código del empleado
     * @return un Optional con el fichaje si existe
     */
    public Optional<Fichaje> getFichajeHoyDe(int codEmpleado) {
        return repo.buscarFichajeHoy(codEmpleado);
    }

    /**
     * Historial del empleado en sesión para el mes actual
     * @return devolver una lista con los fichajes realizados este mes
     */
    public List<Fichaje> getMesActual() {
        LocalDate hoy = LocalDate.now();
        return repo.buscarPorEmpleadoYRango(GestorSesion.getCodEmpleado(), hoy.withDayOfMonth(1), hoy);
    }

    /**
     * Devuelve el historial de fichajes de un empleado en un rango de fechas.
     *
     * @param codEmpleado código del empleado
     * @param desde fecha de inicio incluida
     * @param hasta fecha de fin incluida
     * @return lista de fichajes en el período
     */
    public List<Fichaje> getHistorial(int codEmpleado, LocalDate desde, LocalDate hasta) {
        return repo.buscarPorEmpleadoYRango(codEmpleado, desde, hasta);
    }

    /**
     * Obtiene todos los fichajes de una fecha en concreto.
     *
     * @param fecha día a consultar
     * @return lista de fichajes de esa fecha
     */
    public List<Fichaje> getFichajesPorFecha(LocalDate fecha) {
        return repo.buscarPorFecha(fecha);
    }

    /**
     * Obtiene fichajes de todos los empleados en un rango de fechas,
     * incluyendo los datos básicos de cada empleado.
     *
     * @param desde fecha de inicio incluida
     * @param hasta fecha de fin incluida
     * @return lista de fichajes con información del empleado
     */
    public List<Fichaje> getTodosConEmpleado(LocalDate desde, LocalDate hasta) {
        return repo.listarTodosConEmpleado(desde, hasta);
    }

    /**
     * Actualiza un fichaje entero
     *
     * @param fichaje fichaje con los datos modificados
     * @return {@code true} si se guardó correctamente
     */
    public boolean actualizar(Fichaje fichaje) {
        return repo.actualizar(fichaje);
    }

    /**
     * Elimina un fichaje
     *
     * @param id identificador del fichaje
     * @return {@code true} si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        return repo.eliminar(id);
    }

    /**
     * @return número de empleados que están actualmente fichados (entrada sin salida)
     */
    public int contarFichadosHoy() {
        return repo.contarFichadosHoy();
    }
}
