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

    public NodoAVL<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL<E> derecho) {
        this.derecho = derecho;
    }



}
