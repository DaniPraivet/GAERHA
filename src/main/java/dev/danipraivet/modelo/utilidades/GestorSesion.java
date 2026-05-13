package dev.danipraivet.modelo.utilidades;

import dev.danipraivet.modelo.entidades.Empleado;
import dev.danipraivet.modelo.enumeraciones.Rol;

/**
 * Gestiona la sesion del usuario autenticado. Solo puede haber una sesión activa a la vez.
 * @author Daniel Rodríguez Pérez
 */
public final class GestorSesion {

    private static Empleado empleadoActual = null;

    private GestorSesion() {
    }

    public static void iniciarSesion(Empleado empleado) {
        empleadoActual = empleado;
    }

    public static void cerrarSesion() {
        empleadoActual = null;
    }

    public static boolean haySesionActiva() {
        return empleadoActual != null;
    }

    public static Empleado getEmpleado() {
        return empleadoActual;
    }

    public static int getCodEmpleado() {
        try {
            verificarSesion();
            return empleadoActual.getCodEmpleado();
        } catch (IllegalStateException e) {
            System.out.println("No hay sesion activa. El usuario debe autenticarse primero.");
            return 0;
        }
    }

    public static Rol getRol() {
        try {
            verificarSesion();
            return empleadoActual.getRol();
        } catch (IllegalStateException e) {
            System.out.println("No hay sesion activa. El usuario debe autenticarse primero.");
            return Rol.valueOf("Ninguno");
        }
    }

    public static String getNombreCompleto() {
        try {
            verificarSesion();
            return empleadoActual.getNombreCompleto();
        } catch (IllegalStateException e) {
            System.out.println("No hay sesion activa. El usuario debe autenticarse primero.");
            return "";
        }
    }

    /**
     * @return {@code true} si el usuario en sesión tiene rol EMPLEADO
     */
    public static boolean esEmpleado() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.EMPLEADO;
    }

    /**
     * @return {@code true} si el usuario en sesión tiene rol RRHH
     */
    public static boolean esRRHH() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.RRHH;
    }

    /**
     * @return {@code true} si el usuario en sesión tiene rol ADMIN
     */
    public static boolean esAdmin() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.ADMIN;
    }

    /**
     * @return {@code true} si el usuario puede realizar tareas de gestión (RRHH o ADMIN)
     */
    public static boolean tienePermisoGestion() {
        return esRRHH() || esAdmin();
    }

    private static void verificarSesion() {
        if (empleadoActual == null) {
            throw new IllegalStateException("No hay sesion activa. El usuario debe autenticarse primero.");
        }
    }
}
