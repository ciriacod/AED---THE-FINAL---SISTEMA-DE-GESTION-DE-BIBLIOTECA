package controladores.interfaces;

import modelos.Libro;
// impotacion de una lista

public interface ILibroControlador {
    void registrarLibro(Libro libro);
    void mostrarTodosLosLibros();
    void modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio);
    void eliminarLibro(int codigo);
    void mostrarLibrosPorEstado(String estado);  // Muestra cuando el libro este prestado o disponible
    Libro buscarLibroPorCodigo(int codigo);
    void buscarLibrosPorCriterio(String criterio, String valor);  // Busqueda del libro ya sea por titulo, autor o categoria
}
