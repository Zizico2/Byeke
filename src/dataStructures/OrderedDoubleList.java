package dataStructures;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 * Commands - user input
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;


    /**
     * Node at the head of the list.
     */
    protected DListNode<Entry<K, V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DListNode<Entry<K, V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;

    /**
     * Constructor of an empty doubly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        DListNode<Entry<K, V>> node = findNode(key);
        if (node == null)
            return null;
        else
            return node.getElement().getValue();
    }


    /**
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return
     */
    @Override
    public V insert(K key, V value) {
        V old = null;
        Entry<K, V> entry = new EntryClass<>(key, value);
        if (isEmpty())
            tail = head = new DListNode<>(entry, null, null);
        else {
            old = find(key);
            DListNode<Entry<K, V>> nextNode = head;
            while (nextNode.getElement().getKey().compareTo(key) > 0)
                nextNode = nextNode.getNext();
            if (nextNode.getElement().getKey().equals(key))
                old = nextNode.getElement().getValue();
            nextNode.setElement(entry);
            DListNode<Entry<K, V>> previousNode = nextNode.getPrevious();
            DListNode<Entry<K, V>> node = new DListNode<>(entry, previousNode, nextNode);
                nextNode.setPrevious(node);
            if (previousNode != null)
                previousNode.setNext(node);
        }
        currentSize++;
        return old;
    }

    @Override
    public V remove(K key) {
        DListNode<Entry<K, V>> node = findNode(key);
        currentSize--;
        if (node != null) {
            DListNode<Entry<K, V>> nextNode = node.getNext();
            DListNode<Entry<K, V>> previousNode = node.getPrevious();
            if (previousNode != null)
                previousNode.setNext(nextNode);
            if (nextNode != null)
                nextNode.setPrevious(previousNode);
            return node.getElement().getValue();
        }
        return null;

    }

    private DListNode<Entry<K, V>> findNode(K key) {
        for (DListNode<Entry<K, V>> node = head; node != null; node = node.getNext())
            if (node.getElement().getKey().equals(key))
                return node;
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoublyLLIterator<>(head, tail);
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();
        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();
        return tail.getElement();
    }
}
