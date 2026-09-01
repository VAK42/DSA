import java.util.HashMap;
import java.util.Map;
public class LRUCache<K, V> {
  private static class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;
    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  private final int capacity;
  private final Map<K, Node<K, V>> map;
  private final Node<K, V> head;
  private final Node<K, V> tail;
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>();
    this.head = new Node<>(null, null);
    this.tail = new Node<>(null, null);
    head.next = tail;
    tail.prev = head;
  }
  private void removeNode(Node<K, V> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }
  private void addToHead(Node<K, V> node) {
    node.next = head.next;
    node.next.prev = node;
    node.prev = head;
    head.next = node;
  }
  private void moveToHead(Node<K, V> node) {
    removeNode(node);
    addToHead(node);
  }
  private Node<K, V> removeTail() {
    Node<K, V> res = tail.prev;
    removeNode(res);
    return res;
  }
  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node == null) {
      return null;
    }
    moveToHead(node);
    return node.value;
  }
  public void set(K key, V value) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      node.value = value;
      moveToHead(node);
    } else {
      Node<K, V> newNode = new Node<>(key, value);
      map.put(key, newNode);
      addToHead(newNode);
      if (map.size() > capacity) {
        Node<K, V> tail = removeTail();
        map.remove(tail.key);
      }
    }
  }
  public boolean has(K key) {
    return map.containsKey(key);
  }
  public int size() {
    return map.size();
  }
}