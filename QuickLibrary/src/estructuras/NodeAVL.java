package estructuras;

public class NodeAVL<T> {
    private T dato;
    public NodeAVL<T> next;

    public NodeAVL(T dato) {
        this.dato = dato;
        this.next = null;
    }
    
    public T getData(){
        return this.dato;
    }

    public void setData(T dato){
        this.dato = dato;
    }

    public NodeAVL<T> getNext(){
        return this.next;
    }

    public void setNext(NodeAVL<T> nnext){
        this.next = nnext;
    }
    
}
