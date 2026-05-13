package dev.danipraivet.modelo.utilidades;

import java.util.regex.Pattern;

// Validaciones de formulario centralizadas. Evita duplicar logica en los controladores.
public final class ValidadorFormularios {
    // 8 números y 1 letra entre A y H, J y N, P y T o V-Z (Todas las letras menos la I,O,U y Ñ)
    private static final Pattern PATRON_DNI = Pattern.compile("^[0-9]{8}[A-HJ-NP-TV-Z]$");
    // \w permite letras números y guiones bajos
    // .+- permite puntos y signos
    // [a-z] solo letras
    // {2,} como mínimo 2 caracteres
    private static final Pattern PATRON_EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);
    // Obliga al primer caracter ser 6 o 7, los otros 8 pueden ser cualquier número
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^[67][0-9]{8}$");
    // Obliga al primer caracter ser solo letra, y lo demás cualquier letra, número, punto y guión desde los 3 a los 29 caracteres después de la letra inicial
    private static final Pattern PATRON_USERNAME = Pattern.compile("^[a-zA-Z][a-zA-Z0-9._-]{3,29}$");

    // Tabla de letras del DNI espanol (posicion = numero % 23)
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    private ValidadorFormularios() {
    }

    /**
     * Valida el formato y letra de un DNI español.
     *
     * @param dni cadena con el DNI
     * @return {@code true} si es válido
     */
    public static boolean dniValido(String dni) {
        if (dni == null || !PATRON_DNI.matcher(dni.toUpperCase()).matches()) return false;
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraEsperada = LETRAS_DNI.charAt(numero % 23);
        return Character.toUpperCase(dni.charAt(8)) == letraEsperada;
    }

    /**
     * Valida el formato básico de una dirección de correo electrónico.
     *
     * @param email dirección a comprobar
     * @return {@code true} si parece válida
     */
    public static boolean emailValido(String email) {
        return email != null && PATRON_EMAIL.matcher(email).matches();
    }

    /**
     * Valida un número de teléfono móvil español (empieza por 6 o 7 y 9 dígitos).
     * Se admite {@code null} o vacío (el teléfono es opcional).
     *
     * @param telefono número a validar
     * @return {@code true} si es válido o está vacío
     */
    public static boolean telefonoValido(String telefono) {
        return telefono == null || telefono.isBlank() || PATRON_TELEFONO.matcher(telefono).matches();
    }

    /**
     * Valida el formato del nombre de usuario:
     * empieza por letra, longitud 4-30, admite letras, números, puntos y guiones.
     *
     * @param username nombre de usuario
     * @return {@code true} si cumple el formato
     */
    public static boolean usernameValido(String username) {
        return username != null && PATRON_USERNAME.matcher(username).matches();
    }

    /**
     * Valida que la contrasena tenga mayuscula, minuscula, digito y caracter especial
     * como mínimo debe tener 8 caracteres
     * @param contrasena cadena de texto plano con la contraseña
     * @return {@code true} si la contraseña es segura
     */
    public static boolean contrasenaSegura(String contrasena) {
        if (contrasena == null || contrasena.length() < 8) return false;
        boolean tieneMayuscula = contrasena.chars().anyMatch(Character::isUpperCase);
        boolean tieneMinuscula = contrasena.chars().anyMatch(Character::isLowerCase);
        boolean tieneDigito = contrasena.chars().anyMatch(Character::isDigit);
        boolean tieneEspecial = contrasena.chars().anyMatch(c -> "!@#$%^&*()-_=+[]{}|;:',.<>?".indexOf(c) >= 0);
        return tieneMayuscula && tieneMinuscula && tieneDigito && tieneEspecial;
    }

    /**
     * Comprueba que la cadena no sea {@code null} ni esté en blanco.
     *
     * @param valor cadena a comprobar
     * @return {@code true} si contiene algún carácter no blanco
     */
    public static boolean noVacio(String valor) {
        return valor != null && !valor.isBlank();
    }

    /**
     * Devuelve mensaje de error para un DNI inválido, o null si es correcto
     * @param dni documento nacional de identidad
     * @return mensaje de error dependiendo de como haya sido introducido el dni
     */
    public static String mensajeDni(String dni) {
        if (!noVacio(dni)) return "El DNI no puede estar vacio.";
        if (!PATRON_DNI.matcher(dni.toUpperCase()).matches()) return "Formato invalido. Ejemplo: 12345678A";
        if (!dniValido(dni)) return "La letra del DNI no es correcta.";
        return null;
    }

    /**
     * Devuelve mensaje de error para una contrasena insegura, o null si es correcta
     * @param contrasena cadena de texto plano con la contraseña
     * @return mensaje de error dependiendo de como haya sido introducida la contraseña
     */
    public static String mensajeContrasena(String contrasena) {
        if (!noVacio(contrasena)) return "La contrasena no puede estar vacia.";
        if (contrasena.length() < 8) return "Minimo 8 caracteres.";
        if (!contrasenaSegura(contrasena)) return "Debe incluir mayuscula, minuscula, numero y caracter especial.";
        return null;
    }
}
