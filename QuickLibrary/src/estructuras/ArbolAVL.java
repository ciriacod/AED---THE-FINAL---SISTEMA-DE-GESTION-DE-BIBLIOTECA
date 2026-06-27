package estructuras;

public class ArbolAVL<E extends Comparable<E>>{
    private NodoAVL<E> raiz;
    
    public ArbolAVL() {
        raiz = null;
    }

    //Inserta un elemento en el árbol
    
    public void insertar(E dato) {
        raiz = insertar(raiz, dato);
    }

    private NodoAVL<E> insertar(NodoAVL<E> nodo, E dato) {

        if (nodo == null) return new NodoAVL<>(dato);

        if (dato.compareTo(nodo.getDato()) < 0) {

            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), dato));

        } else if (dato.compareTo(nodo.getDato()) > 0) {

            nodo.setDerecho(insertar(nodo.getDerecho(), dato));

        } else {

            return nodo;
        }

        actualizarAltura(nodo);

        return balancear(nodo);
    }

    //Busca un dato en el árbol
    
    public E buscar(E dato) {

        NodoAVL<E> resultado = buscarNodo(raiz, dato);

        return resultado != null ? resultado.getDato() : null;
    }

    private NodoAVL<E> buscarNodo(NodoAVL<E> nodo, E dato) {

        if (nodo == null) return null;

        if (dato.compareTo(nodo.getDato()) == 0) return nodo;

        if (dato.compareTo(nodo.getDato()) < 0) return buscarNodo(nodo.getIzquierdo(), dato);

        return buscarNodo(nodo.getDerecho(), dato);
    }

    
    public NodoAVL<E> getRaiz() {
        return raiz;
    }
}

