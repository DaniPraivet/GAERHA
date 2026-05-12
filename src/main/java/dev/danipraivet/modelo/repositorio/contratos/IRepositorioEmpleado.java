package dev.danipraivet.modelo.repositorio.contratos;

import dev.danipraivet.modelo.entidades.Empleado;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de acceso a datos para empleados. Desacopla la logica de negocio de los detalles JDBC
 * @author Daniel Rodríguez Pérez
 */
public interface IRepositorioEmpleado {
    /**
     * Busca un empleado por su código
     *
     * @param codEmpleado código numérico del empleado
     * @return un Optional con el empleado si existe
     */
    Optional<Empleado> buscarPorCodigo(int codEmpleado);

    /**
     * Busca un empleado por su nombre de usuario
     *
     * @param username nombre de usuario
     * @return un Optional con el empleado si existe
     */
    Optional<Empleado> buscarPorUsername(String username);

    /**
     * Busca un empleado por su DNI
     *
     * @param dni documento nacional de identidad
     * @return un Optional con el empleado si existe
     */
    Optional<Empleado> buscarPorDni(String dni);

    /**
     * Lista todos los empleados activos (no dados de baja).
     *
     * @return lista de empleados activos
     */
    List<Empleado> listarTodos();

    /**
     * Lista todos los empleados, incluidos los que están de baja.
     *
     * @return lista completa de empleados
     */
    List<Empleado> listarTodosIncluyendoBajas();

    /**
     * Inserta un nuevo empleado en la base de datos.
     *
     * @param empleado empleado a insertar
     * @return {@code true} si la inserción fue exitosa
     */
    boolean insertar(Empleado empleado);

    /**
     * Actualiza los datos de un empleado existente.
     *
     * @param empleado empleado con los datos actualizados
     * @return {@code true} si la actualización fue exitosa
     */
    boolean actualizar(Empleado empleado);

    /**
     * Desactiva el empleado sin borrar sus datos ni fichajes
     * @param codEmpleado valor numérico identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean darDeBaja(int codEmpleado);

    /**
     * Reactiva un empleado que estaba de baja
     * @param codEmpleado valor numérico identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean recuperar(int codEmpleado);

    /**
     * Eliminación total
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
     * Resetea intentos fallidos y actualiza la fecha de último acceso
     * @param username nombre identificador del empleado
     * @return si se ha realizado correctamente la operación o no
     */
    boolean registrarLoginExitoso(String username);

    /**
     * Comprueba si ya existe un empleado con el nombre de usuario dado
     *
     * @param username nombre de usuario a verificar
     * @return {@code true} si el username ya está en uso
     */
    boolean existeUsername(String username);

    /**
     * Comprueba si ya existe un empleado con el DNI dado.
     *
     * @param dni DNI a verificar
     * @return {@code true} si el DNI ya está registrado
     */
    boolean existeDni(String dni);

    /**
     * @return número total de empleados registrados (activos + baja)
     */
    int contarTotalRegistrados();

    /**
     * @return número de empleados que han accedido hoy
     */
    int contarActivosHoy();

    /**
     * @return número de cuentas actualmente bloqueadas
     */
    int contarBloqueados();

    /**
     * @return número de usuarios con rol RRHH que están activos
     */
    int contarRrhhActivos();
}
