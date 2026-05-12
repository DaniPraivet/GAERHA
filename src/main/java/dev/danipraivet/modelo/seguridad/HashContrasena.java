package dev.danipraivet.modelo.seguridad;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase encargada de cifrar las contraseñas
 * @author Daniel Rodríguez Pérez
 */
public final class HashContrasena {

    private static final int COSTE = 12;

    private HashContrasena() {
    }

    /**
     * Genera el hash BCrypt de una contraseña en texto plano
     * @param contrasena cadena de texto a cifrar
     * @return cadena de longitud fija que representa la contraseña
     */
    public static String hashear(String contrasena) {
        if (contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException("La contrasena no puede estar vacia.");
        }
        return BCrypt.hashpw(contrasena, BCrypt.gensalt(COSTE));
    }

    /**
     * Verifica si una contrasena en texto plano coincide con su hash almacenado
     * @param contrasenaPlana cadena de texto a cifrar
     * @param hash cadena de caracteres de longitud fija que representa la contraseña
     * @return si el hash representa la contraseña o no
     */
    public static boolean verificar(String contrasenaPlana, String hash) {
        if (contrasenaPlana == null || hash == null) return false;
        try {
            return BCrypt.checkpw(contrasenaPlana, hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
