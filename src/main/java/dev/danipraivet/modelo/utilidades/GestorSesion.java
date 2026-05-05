package dev.danipraivet.modelo.utilidades;

import dev.danipraivet.modelo.entidades.Empleado;
import dev.danipraivet.modelo.enumeraciones.Rol;

// Gestiona la sesion del usuario autenticado. Solo puede haber una sesion activa a la vez.
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

    public static boolean esEmpleado() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.EMPLEADO;
    }

    public static boolean esRRHH() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.RRHH;
    }

    public static boolean esAdmin() {
        return haySesionActiva() && empleadoActual.getRol() == Rol.ADMIN;
    }

    public static boolean tienePermisoGestion() {
        return esRRHH() || esAdmin();
    }

    private static void verificarSesion() {
        if (empleadoActual == null) {
            throw new IllegalStateException("No hay sesion activa. El usuario debe autenticarse primero.");
        }
    }
}
