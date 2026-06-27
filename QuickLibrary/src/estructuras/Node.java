package estructuras;

public class Node<T> {
    private T dato;
    public Node<T> next;

    public Node(T dato) {
        this.dato = dato;
        this.next = null;
    }
    
    public T getData(){
        return this.dato;
    }

    public void setData(T dato){
        this.dato = dato;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public void setNext(Node<T> nnext){
        this.next = nnext;
    }
    
}
