import java.util.ArrayList;

/**
 * Implementation of Hashtable using Separate Chaining method
 */

class HashNode<K, V> {
    K key;
    V value;

    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class Map<K, V> {

    private ArrayList<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;

    private static double LOAD_FACTOR_THRESHOLD = 0.7d;

    public Map() {
        this.bucketArray = new ArrayList<>();
        this.numBuckets = 10;
        this.size = 0;

        for (int i = 0; i < numBuckets; i++)
            this.bucketArray.add(null);

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHashBucketIndex(K key) {
        int hashcode = key.hashCode();
        return hashcode % numBuckets;
    }

    public V remove(K key) {
        int bucketIndex = this.getHashBucketIndex(key);

        HashNode<K, V> head = this.bucketArray.get(bucketIndex);

        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key))
                break;
            prev = head;
            head = head.next;
        }
        if (head == null) //means key not found
            return null;

        this.size--;


        if (prev != null)
            prev.next = head.next;
        else //we're removing first element in linked list
            this.bucketArray.set(bucketIndex, head.next);

        return head.value;
    }

    public V get(K key) {
        int bucketIndex = this.getHashBucketIndex(key);
        HashNode<K, V> head = this.bucketArray.get(bucketIndex);

        //if key exists, get value, else return null
        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public void add(K key, V value) {
        int bucketIndex = this.getHashBucketIndex(key);
        HashNode<K, V> head = this.bucketArray.get(bucketIndex);

        //if key exists, replace value and return
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        //we add new key value pair in appropriate bucket
        this.size++;
        head = this.bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        this.bucketArray.set(bucketIndex, newNode);

        //if load factor is beyond threshold, double bucket size and redistribute stored data
        if ((1.0 * this.size) / this.numBuckets >= LOAD_FACTOR_THRESHOLD) {
            ArrayList<HashNode<K, V>> tempArray = bucketArray;
            this.bucketArray = new ArrayList<>();
            this.numBuckets = 2 * this.numBuckets;
            this.size = 0;

            for (int i = 0; i < this.numBuckets; i++) {
                this.bucketArray.add(null);
            }
            for (HashNode<K, V> headNode : tempArray) {
                while (headNode != null) {
                    this.add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
