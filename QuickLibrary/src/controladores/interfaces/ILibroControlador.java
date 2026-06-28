package controladores.interfaces;

import modelos.Libro;

public interface ILibroControlador {
    void registrarLibro(Libro libro);
    Libro buscarLibroPorCodigo(int codigo);
}
