import java.util.ArrayList;
import java.util.List;

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

    private List<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;

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

        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }
}
