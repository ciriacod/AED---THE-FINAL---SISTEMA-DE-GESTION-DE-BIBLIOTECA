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

            nodo.setIzquierdo(
                    insertar(nodo.getIzquierdo(), dato));

        } else if (dato.compareTo(nodo.getDato()) > 0) {

            nodo.setDerecho(
                    insertar(nodo.getDerecho(), dato));

        } else {

            return nodo;
        }

        actualizarAltura(nodo);

        return balancear(nodo);
    }

    
    public NodoAVL<E> getRaiz() {
        return raiz;
    }

}

