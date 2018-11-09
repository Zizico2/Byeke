package dataStructures;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */


public class EntryClass<K, V> implements Entry<K, V> {

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
}
