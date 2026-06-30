# Manual de Usuario

## Sistema de Gestión de Préstamos de Libros

Bienvenido al Manual de Usuario de **QuickLibrary**, una aplicación de consola desarrollada en **Java** diseñada para administrar de manera eficiente el catálogo de una biblioteca y controlar el flujo de solicitudes y préstamos utilizando estructuras de datos avanzadas (**Árbol AVL** y **Cola Enlazada**).

---

## Requisitos e Instalación

### Requisitos del Sistema

- Java JDK 8 o superior.
- Consola de comandos (Terminal, CMD o PowerShell) o la terminal integrada de un IDE compatible (IntelliJ IDEA, Eclipse, NetBeans o Visual Studio Code).

### Inicio Rápido

1. Abra el proyecto en su entorno de desarrollo.
2. Ejecute la clase principal ubicada en:

```text
src/vista/MenuPrincipal.java
```

Al iniciar la aplicación, el sistema cargará automáticamente la información almacenada en el archivo **test.csv**. Si el archivo no existe, el sistema iniciará con un catálogo vacío listo para registrar nuevos libros.

---

## Interfaz y Navegación Principal

Al iniciar la aplicación se mostrará el **Menú Principal** en la consola.

Para utilizar el sistema, ingrese el número correspondiente a la opción deseada y presione **ENTER**.

> **Nota:** Si se ingresa un valor no numérico en un menú que espera números, el sistema mostrará un mensaje de error indicando que debe ingresar un número entero válido y permitirá volver a intentar sin cerrar la aplicación.

---

# Guía de Opciones del Menú

## Gestión del Catálogo

### Opción 1: Registrar libro

Permite registrar un nuevo libro en el catálogo.

Datos solicitados:

- Código (número entero único).
- Título.
- Autor.
- Categoría.
- Año de publicación.

El libro se registra automáticamente con estado **Disponible**.

---

### Opción 2: Mostrar libros

Muestra en pantalla todos los libros registrados en el catálogo, administrados mediante un **Árbol AVL**.

---

### Opción 3: Buscar libro por código

Realiza una búsqueda rápida utilizando el código único del libro.

Si el libro existe, se mostrarán todos sus datos; de lo contrario, aparecerá el mensaje:

```text
Libro no encontrado.
```

---

### Opción 4: Buscar libros

Abre un submenú con las siguientes opciones:

- Buscar por categoría.
- Buscar por título.
- Buscar por autor.
- Mostrar únicamente libros disponibles.
- Mostrar únicamente libros prestados.

---

### Opción 5: Modificar libro

Permite actualizar la información de un libro existente.

Proceso:

1. Ingresar el código del libro.
2. Si el libro existe, el sistema solicitará los nuevos datos:
   - Título.
   - Autor.
   - Categoría.
   - Año de publicación.

---

### Opción 6: Eliminar libro

Elimina un libro del catálogo utilizando su código.

---

## Gestión de Solicitudes y Préstamos

### Opción 7: Registrar solicitud de préstamo

Registra una nueva solicitud siguiendo el principio **FIFO (First In, First Out)** mediante una **Cola Enlazada**.

Datos requeridos:

- Código del estudiante.
- Nombre del estudiante.
- Código del libro.

La fecha y hora de la solicitud se registran automáticamente.

---

### Opción 8: Mostrar cola de solicitudes

Muestra todas las solicitudes pendientes respetando el orden en que fueron registradas.

---

### Opción 9: Atender siguiente solicitud

Procesa la primera solicitud de la cola.

El sistema verifica automáticamente la disponibilidad del libro.

- Si el libro está disponible:
  - Cambia su estado a **Prestado**.
  - Elimina la solicitud de la cola.

- Si el libro no está disponible:
  - El sistema informa que el préstamo no puede realizarse.

---

### Opción 10: Registrar devolución

Permite devolver un libro previamente prestado.

Proceso:

1. Ingresar el código del libro.
2. El sistema cambia su estado de **Prestado** a **Disponible**.

---

## Reportes y Cierre

### Opción 11: Mostrar reporte y exportar

Genera un resumen del estado actual de la biblioteca mostrando:

- Total de libros registrados.
- Libros disponibles.
- Libros prestados.
- Solicitudes pendientes.

Después del reporte, el sistema preguntará:

```text
¿Desea exportar una copia del catálogo actual a un archivo CSV? (S/N)
```

Si la respuesta es **S**, se solicitará el nombre del archivo.

El sistema detecta automáticamente si el usuario escribe la extensión `.csv`, evitando duplicarla y confirmando la exportación correctamente.

---

### Opción 12: Salir

Finaliza la aplicación de forma segura.

Antes de cerrar, el sistema guarda automáticamente toda la información en el archivo **test.csv**, preservando los cambios realizados durante la sesión.

---

# Ejemplos de Uso

## Ejemplo 1: Registrar y atender una solicitud

Seleccione la opción **7** e ingrese la información solicitada.

```text
Código del estudiante: STU987
Nombre del estudiante: Carlos Yepez
Código del libro solicitado: 101
```

Posteriormente seleccione la opción **9** para atender la solicitud.

```text
--- ATENDIENDO SIGUIENTE SOLICITUD ---

Verificando disponibilidad del libro Código: 101...

Libro asignado con éxito.
Estado del libro actualizado a: Prestado.
```

---

## Ejemplo 2: Exportar el catálogo

Seleccione la opción **11**.

El sistema mostrará el reporte y luego preguntará:

```text
¿Desea exportar una copia del catálogo actual a un archivo CSV? (S/N): S

Ingrese el nombre del archivo: biblioteca_2026

Catálogo exportado exitosamente como: biblioteca_2026.csv
```