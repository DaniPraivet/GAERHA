package dev.danipraivet.modelo.repositorio.contratos;

import dev.danipraivet.modelo.entidades.Empleado;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de acceso a datos para empleados. Desacopla la logica de negocio de los detalles JDBC
 * @author Daniel Rodríguez Pérez
 */
public interface IRepositorioEmpleado {

    Optional<Empleado> buscarPorCodigo(int codEmpleado);

    Optional<Empleado> buscarPorUsername(String username);

    Optional<Empleado> buscarPorDni(String dni);

    List<Empleado> listarTodos();

    List<Empleado> listarTodosIncluyendoBajas();

    boolean insertar(Empleado empleado);

    boolean actualizar(Empleado empleado);

    /**
     * Baja logica: desactiva el empleado sin borrar sus datos ni fichajes
     * @param codEmpleado valor numérico identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean darDeBaja(int codEmpleado);

    /**
     * Reactivacion logica: reactiva un empleado que estaba de baja
     * @param codEmpleado valor numérico identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean recuperar(int codEmpleado);

    /**
     * Eliminacion total
     * @param codEmpleado valor numérico identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean eliminar(int codEmpleado);

    /**
     * Incrementa el contador de intentos fallidos. Bloquea la cuenta si llega a 5
     * @param username nombre identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean registrarIntentoFallido(String username);

    /**
     * Resetea intentos fallidos y actualiza la fecha de ultimo acceso
     * @param username nombre identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean registrarLoginExitoso(String username);

    boolean existeUsername(String username);

    boolean existeDni(String dni);

    int contarTotalRegistrados();

    int contarActivosHoy();

    int contarBloqueados();

    int contarRrhhActivos();
}
