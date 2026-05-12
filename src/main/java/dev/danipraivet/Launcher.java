package dev.danipraivet;

import dev.danipraivet.vista.Aplicacion;

/**
 * Punto de entrada principal de la aplicación.  {@link dev.danipraivet.vista.Aplicacion}
 * para evitar conflictos con el módulo JavaFX en entornos sin classpath explícito.
 * @author Daniel Rodríguez Pérez
 */
public class Launcher {
    /**
     * Punto de entrada que delega en {@link Aplicacion#main(String[])}
     * para evitar conflictos de módulos JavaFX en ciertos entornos.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        Aplicacion.main(args);
    }
}
