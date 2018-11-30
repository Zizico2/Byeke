package dataStructures;

public class OrderedDoubleListIterator<K,V> implements Iterator<Entry<K,V>> {
    /**
     * Constant for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Table to be Iterated
     */
    private Dictionary<K,V>[] table;

    /**
     * Current iterator
     */
    private Iterator<Entry<K,V>> iterator;


    /**
     * Current Dictionary
     */
    private int position;

    /**
     *
     * @param table table to be iterated
     */
    protected OrderedDoubleListIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }


    @Override
    public boolean hasNext() {
        return table.length -1 > position || iterator.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();
        if (!iterator.hasNext()) {
            findNextList();
        }
        return iterator.next();
    }

    @Override
    public void rewind() {
        position = -1;
        findNextList();
    }

    private void findNextList() {
        for (;position<table.length;position++)
            if (!table[position].isEmpty()){
                iterator = table[position].iterator();
                return;
            }
        iterator = null;
    }
}
