package estructuras;

public class NodoAVL<E> {

    private E dato;
    private NodoAVL<E> izquierdo;
    private NodoAVL<E> derecho;
    private int altura;

    public NodoAVL(E dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }




}
