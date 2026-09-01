import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
public class HashTable<K, V> {
  private static class Entry<K, V> {
    K key;
    V value;
    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  private static final int defaultCapacity = 32;
  private LinkedList<Entry<K, V>>[] buckets;
  private int size;
  @SuppressWarnings("unchecked")
  public HashTable(int capacity) {
    buckets = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      buckets[i] = new LinkedList<>();
    }
    size = 0;
  }
  public HashTable() {
    this(defaultCapacity);
  }
  private int getBucketIndex(K key) {
    int hashCode = key == null ? 0 : key.hashCode();
    return (hashCode & 0x7fffffff) % buckets.length;
  }
  public void set(K key, V value) {
    int index = getBucketIndex(key);
    LinkedList<Entry<K, V>> bucket = buckets[index];
    for (Entry<K, V> entry : bucket) {
      if (Objects.equals(entry.key, key)) {
        entry.value = value;
        return;
      }
    }
    bucket.add(new Entry<>(key, value));
    size++;
  }
  public V get(K key) {
    int index = getBucketIndex(key);
    LinkedList<Entry<K, V>> bucket = buckets[index];
    for (Entry<K, V> entry : bucket) {
      if (Objects.equals(entry.key, key)) {
        return entry.value;
      }
    }
    return null;
  }
  public V delete(K key) {
    int index = getBucketIndex(key);
    LinkedList<Entry<K, V>> bucket = buckets[index];
    for (int i = 0; i < bucket.size(); i++) {
      Entry<K, V> entry = bucket.get(i);
      if (Objects.equals(entry.key, key)) {
        bucket.remove(i);
        size--;
        return entry.value;
      }
    }
    return null;
  }
  public boolean has(K key) {
    return get(key) != null;
  }
  public List<K> getKeys() {
    List<K> keys = new ArrayList<>();
    for (LinkedList<Entry<K, V>> bucket : buckets) {
      for (Entry<K, V> entry : bucket) {
        keys.add(entry.key);
      }
    }
    return keys;
  }
  public List<V> getValues() {
    List<V> values = new ArrayList<>();
    for (LinkedList<Entry<K, V>> bucket : buckets) {
      for (Entry<K, V> entry : bucket) {
        values.add(entry.value);
      }
    }
    return values;
  }
  public int size() {
    return size;
  }
  public boolean isEmpty() {
    return size == 0;
  }
}