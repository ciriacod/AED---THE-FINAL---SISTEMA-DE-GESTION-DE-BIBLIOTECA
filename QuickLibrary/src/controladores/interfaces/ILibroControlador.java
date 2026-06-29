package controladores.interfaces;

import modelos.Libro;
// impotacion de una lista

public interface ILibroControlador {
    void registrarLibro(Libro libro);
    void mostrarTodosLosLibros();
    void modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio);
    void eliminarLibro(int codigo);
    Libro buscarLibroPorCodigo(int codigo);
    
    void buscarLibroPorTitulo(String titulo);
    void buscarLibroPorAutor(String autor);
    void buscarLibroPorCategoria(String categoria);
    void mostrarLibrosDisponibles();
    void mostrarLibrosPrestados();
}
