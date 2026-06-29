package estructuras;

public class ArbolAVL<E extends Comparable<E>>{
    private NodoAVL<E> raiz;

    // nuestro constructor
    public ArbolAVL() {
        raiz = null;
    }
    // aqui comenzamos la insercion
    public void insertar(E dato) {
        raiz = insertar(raiz, dato);
    }
    // este metodo inserta el dato en su posicion
    private NodoAVL<E> insertar(NodoAVL<E> nodo, E dato) {

        if (nodo == null) 
            return new NodoAVL<>(dato);

        // si el dato es menor va por la izquierda
        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), dato));

        // si el dato es mayor va por la derecha
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setDerecho(insertar(nodo.getDerecho(), dato));

        } else {
            return nodo;
        }

        actualizarAltura(nodo);
        // verificamos si necesita balancearse
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

    private NodoAVL<E> balancear(NodoAVL<E> nodo) {

        int factor = factorBalance(nodo);

        // Izquierda
        if (factor > 1) {

            if (factorBalance(nodo.getIzquierdo()) < 0) {

                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            }

            return rotacionDerecha(nodo);
        }

        // Derecha
        if (factor < -1) {

            if (factorBalance(nodo.getDerecho()) > 0) {

                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            }

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL<E> rotacionDerecha(NodoAVL<E> y) {

        NodoAVL<E> x = y.getIzquierdo();
        NodoAVL<E> t2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(t2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVL<E> rotacionIzquierda(NodoAVL<E> x) {

        NodoAVL<E> y = x.getDerecho();
        NodoAVL<E> t2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(t2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    private int altura(NodoAVL<E> nodo) {

        if (nodo == null)
            return 0;

        return nodo.getAltura();
    }

    private void actualizarAltura(NodoAVL<E> nodo) {

        nodo.setAltura(1 +Math.max(altura(nodo.getIzquierdo()),altura(nodo.getDerecho())));
    }

    private int factorBalance(NodoAVL<E> nodo) {

        if (nodo == null)
            return 0;

        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    public void inOrden() {

        inOrden(raiz);

        System.out.println();
    }

    private void inOrden(NodoAVL<E> nodo) {

        if (nodo != null) {

            inOrden(nodo.getIzquierdo());

            System.out.println(nodo.getDato());

            inOrden(nodo.getDerecho());
        }
    }

    public void preOrden() {

        preOrden(raiz);

        System.out.println();
    }

    private void preOrden(NodoAVL<E> nodo) {

        if (nodo != null) {

            System.out.println(nodo.getDato());

            preOrden(nodo.getIzquierdo());

            preOrden(nodo.getDerecho());
        }
    }

    public void postOrden() {

        postOrden(raiz);

        System.out.println();
    }

    private void postOrden(NodoAVL<E> nodo) {

        if (nodo != null) {

            postOrden(nodo.getIzquierdo());

            postOrden(nodo.getDerecho());

            System.out.println(nodo.getDato());
        }
    }

    public int contar() {
        return contar(raiz);
    }

    private int contar(NodoAVL<E> nodo) {

        if (nodo == null)
            return 0;

        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void limpiar() {
        raiz = null;
    }

    public int getAltura() {
        return altura(raiz);
    }
    
    public NodoAVL<E> getRaiz() {
        return raiz;
    }
}

