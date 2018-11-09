package dataStructures;

import java.util.HashMap;
import java.util.Map;

/**
 * Chained Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class ChainedHashTable<K extends Comparable<K>, V>
        extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleList<K, V>();
        maxSize = capacity;
        currentSize = 0;
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
    }


    public ChainedHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();
        currentSize++;
        return table[hash(key)].insert(key, value);
    }

    @Override
    public V remove(K key) {
        if (table[hash(key)].isEmpty())
            return null;
        currentSize--;
        return table[hash(key)].remove(key);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        //TODO: Left as an exercise.
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void rehash() {
        Dictionary<K, V>[] oldTable = table;
        int arraySize = HashTable.nextPrime((2 * table.length));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleList<K, V>();
        maxSize = table.length;
        currentSize = 0;
        for (int i = 0; i < oldTable.length; i++) {
            Iterator<Entry<K, V>> iterator = oldTable[i].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                table[i].insert(entry.getKey(), entry.getValue());
            }
        }
    }
}
































