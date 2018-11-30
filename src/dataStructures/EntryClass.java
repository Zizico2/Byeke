package dataStructures;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */


public class EntryClass<K, V> implements Entry<K, V> {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private K key;
    private V value;

    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setKey(K newKey) {
        key = newKey;
    }

    @Override
    public void setValue(V newValue) {
        value = newValue;
    }
}
