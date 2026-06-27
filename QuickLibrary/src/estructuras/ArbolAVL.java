package estructuras;

public class ArbolAVL<E extends Comparable<E>>{
    private NodoAVL<E> raiz;
    
    public ArbolAVL() {
        raiz = null;
    }

    
    public NodoAVL<E> getRaiz() {
        return raiz;
    }

}

