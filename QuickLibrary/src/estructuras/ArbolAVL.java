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

        if (nodo == null) 
            return new NodoAVL<>(dato);

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

    //Busca un objeto en el árbol

    public E buscar(E dato) {

        NodoAVL<E> resultado = buscarNodo(raiz, dato);

        return resultado != null ? resultado.getDato() : null;
    }

    private NodoAVL<E> buscarNodo(NodoAVL<E> nodo, E dato) {

        if (nodo == null) 
            return null;

        if (dato.compareTo(nodo.getDato()) == 0) 
            return nodo;

        if (dato.compareTo(nodo.getDato()) < 0) 
            return buscarNodo(nodo.getIzquierdo(), dato);

        return buscarNodo(nodo.getDerecho(), dato);
    }
    
    //Elimina un elemento del árbol

    public void eliminar(E dato) {
        raiz = eliminar(raiz, dato);
    }

    private NodoAVL<E> eliminar(NodoAVL<E> nodo, E dato) {

        if (nodo == null) 
            return null;

        if (dato.compareTo(nodo.getDato()) < 0) {

            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), dato));

        } else if (dato.compareTo(nodo.getDato()) > 0) {

            nodo.setDerecho(eliminar(nodo.getDerecho(), dato));

        } else {

            // Sin hijos
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) 
                return null;

            // Un hijo derecho
            if (nodo.getIzquierdo() == null) 
                return nodo.getDerecho();

            // Un hijo izquierdo
            if (nodo.getDerecho() == null) 
                return nodo.getIzquierdo();

            // Dos hijos
            NodoAVL<E> sucesor = minimo(nodo.getDerecho());

            nodo.setDato(sucesor.getDato());

            nodo.setDerecho(eliminar(nodo.getDerecho(),sucesor.getDato()));
        }

        actualizarAltura(nodo);

        return balancear(nodo);
    }

    //Obtiene el menor nodo
    
    private NodoAVL<E> minimo(NodoAVL<E> nodo) {

        while (nodo.getIzquierdo() != null) 
            nodo = nodo.getIzquierdo();

        return nodo;
    }

    
    public NodoAVL<E> getRaiz() {
        return raiz;
    }
}

