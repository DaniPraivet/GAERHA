# GAERHA

**G**estor de **A**sistencia para **E**mpleados, **R**RHH y **A**dministradores.

Aplicación de escritorio para el control de fichajes, gestión de empleados y generación de informes. Hecha con JavaFX y MySQL.


### Inicio de sesión
<img src="https://github.com/DaniPraivet/GAERHA/blob/master/src/main/resources/dev/danipraivet/imagenes/ISU.png" width=400></img>

### Empleado
<img src="https://github.com/DaniPraivet/GAERHA/blob/master/src/main/resources/dev/danipraivet/imagenes/Empleado.png" width=400></img>

### Recursos humanos
<img src="https://github.com/DaniPraivet/GAERHA/blob/master/src/main/resources/dev/danipraivet/imagenes/RRHH.png" width=400></img>

### Admin
<img src="https://github.com/DaniPraivet/GAERHA/blob/master/src/main/resources/dev/danipraivet/imagenes/Admin.png" width=400></img>

---

## Requisitos mínimos

- Java 21 (recomendado Microsoft OpenJDK 21)
- MySQL 8.0 o superior funcionando

---

## Cómo instalarlo

1. Descarga el `.zip` desde [Releases](https://github.com/DaniPraivet/GAERHA/releases).
2. Descomprímelo donde quieras.
3. En un servidor MySQL (recomendado la 9.6) y ejecuta el archivo `script.sql` que viene dentro. Esto crea la base de datos y mete datos de prueba.
4. Si tu base de datos no está en `localhost:3306` o cambiaste las contraseñas, edita `db.properties`.
5. Ejecuta el archivo `.bat`. Si estás en Linux o Mac, usa `java -jar GAERHA-1.0.0.jar` desde el terminal.

---

## Usuarios de prueba

Estos son algunos de los usuarios de prueba, si quieres verlos todos puedes acceder al usuario de recursos humanos y tienes libertad de añadir o dar de baja los que quieras.

| Usuario      | Contraseña  | Rol      |
|--------------|-------------|----------|
| cgarcia      | Admin1234!  | ADMIN    |
| mmartinez    | RRHH1234!   | RRHH     |
| jrodriguez   | Emp1234!    | EMPLEADO |

El resto de empleados usan la misma contraseña que `jrodriguez` en los datos de prueba.

---

## Cómo funciona

- **Empleado**: ficha la entrada y salida con un botón. Ve su historial y horas del mes.
- **RRHH**: gestiona empleados (altas, bajas, editar datos), consulta fichajes y puede exportar informes en PDF o Excel.
- **Admin**: panel de control con estadísticas, puede modificar/borrar fichajes, eliminar empleados y desbloquear cuentas.

La sesión se cierra sola tras 3 minutos sin tocar nada. Tras 5 intentos fallidos la cuenta se bloquea por seguridad.

---

## Licencia


Copyright 2026. MIT

Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.

EL SOFTWARE SE PROPORCIONA "COMO ESTÁ", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
