package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 1L;

    private Stack<BSTNode<K,V>> stack;

    private BSTNode<K,V> root;

    public BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        stack = new StackInList<>();
        rewind();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();
        BSTNode<K,V> node = stack.pop();
        if(node.getRight() != null)
            stackLeftSubTree(node.getRight());
        return node.getEntry();
    }

    private void stackLeftSubTree(BSTNode<K, V> root) {
        BSTNode<K,V> node = root;
        while(node != null){
            stack.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public void rewind() {
        stackLeftSubTree(root);
    }
}
