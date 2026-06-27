package estructuras;

public class LinkedQueue<E>{
    private NodeAVL<E> first;
    private NodeAVL<E> last;
    private int size;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void enqueue(E data) {
        NodeAVL<E> newNode = new NodeAVL<E>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            System.out.println("Cola Vacia, Nada que Desencolar");
            return null;
        }
        E data = first.getData();
        first = first.getNext();
        size--;
        
        if (first == null) {
            last = null;
        }
        return data;
    }

    public E peek(){
        return isEmpty() ? null : first.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return this.size;
    }

    public void mostrar(){
        if (isEmpty()) {
            System.out.println("Lista de solicitudes esta vacia.");
            return;
        }
        NodeAVL<E> actual = first;
        int index = 1;
        while (actual != null) {
            System.out.println(index + ". " + actual.getData().toString());
            actual = actual.getNext();
            index++;
        }
    }
}
