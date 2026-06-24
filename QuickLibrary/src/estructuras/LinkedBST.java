package estructuras;

public class LinkedBST<E extends Comparable<E>>{

    protected class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) {
        this.root = insertRec(x, this.root);
    }

    protected Node<E> insertRec(E x, Node<E> node) {
        if (node == null) {
            return new Node<>(x);
        }
        
        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRec(x, node.left);
        } else if (cmp > 0) {
            node.right = insertRec(x, node.right);
        }
        return node;
    }

    public E search(E x) {
        Node<E> result = searchRec(x, root);
        return (result == null) ? null : result.data;
    }

    private Node<E> searchRec(E x, Node<E> node) {
        if (node == null) return null;

        int cmp = x.compareTo(node.data);         
        if (cmp == 0) return node;
        
        return (cmp < 0) ? searchRec(x, node.left) : searchRec(x, node.right);
    }

    public void delete(E x) {
        this.root = removeRec(x, this.root);
    }

    private Node<E> removeRec(E x, Node<E> node) {
        if (node == null) return null;
        
        int cmp = x.compareTo(node.data); 
        if (cmp < 0) {
            node.left = removeRec(x, node.left);
        } else if (cmp > 0) {
            node.right = removeRec(x, node.right);
        } else {
            if (node.left != null && node.right != null) {
                Node<E> min = findMin(node.right);
                node.data = min.data;
                node.right = removeRec(min.data, node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
        }
        return node;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }
    }

    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return 1
                + countAllNodes(node.left)
                + countAllNodes(node.right);
    }

    private Node<E> findMin(Node<E> node) {

        Node<E> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

}