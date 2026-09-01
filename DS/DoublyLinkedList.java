import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class DoublyLinkedList<T> {
  public static class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;
    public Node(T value, Node<T> next, Node<T> prev) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
    public Node(T value) {
      this(value, null, null);
    }
  }
  public Node<T> head;
  public Node<T> tail;
  private int size;
  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  public DoublyLinkedList<T> prepend(T value) {
    Node<T> n = new Node<>(value, head, null);
    if (head != null) {
      head.prev = n;
    }
    head = n;
    if (tail == null) {
      tail = n;
    }
    size++;
    return this;
  }
  public DoublyLinkedList<T> append(T value) {
    Node<T> n = new Node<>(value, null, tail);
    if (tail != null) {
      tail.next = n;
    }
    tail = n;
    if (head == null) {
      head = n;
    }
    size++;
    return this;
  }
  public T delete(T value) {
    if (head == null) {
      return null;
    }
    T deleted = null;
    Node<T> curr = head;
    while (curr != null) {
      if (Objects.equals(curr.value, value)) {
        deleted = curr.value;
        if (curr == head) {
          head = curr.next;
          if (head != null) {
            head.prev = null;
          } else {
            tail = null;
          }
        } else if (curr == tail) {
          tail = curr.prev;
          tail.next = null;
        } else {
          curr.prev.next = curr.next;
          curr.next.prev = curr.prev;
        }
        size--;
      }
      curr = curr.next;
    }
    return deleted;
  }
  public T deleteHead() {
    if (head == null) {
      return null;
    }
    T val = head.value;
    head = head.next;
    if (head != null) {
      head.prev = null;
    } else {
      tail = null;
    }
    size--;
    return val;
  }
  public T deleteTail() {
    if (tail == null) {
      return null;
    }
    T val = tail.value;
    tail = tail.prev;
    if (tail != null) {
      tail.next = null;
    } else {
      head = null;
    }
    size--;
    return val;
  }
  public Node<T> find(T value) {
    Node<T> curr = head;
    while (curr != null) {
      if (Objects.equals(curr.value, value)) {
        return curr;
      }
      curr = curr.next;
    }
    return null;
  }
  public boolean contains(T value) {
    return find(value) != null;
  }
  public DoublyLinkedList<T> reverse() {
    Node<T> curr = head;
    Node<T> prev = null;
    Node<T> next = null;
    while (curr != null) {
      next = curr.next;
      prev = curr.prev;
      curr.next = prev;
      curr.prev = next;
      curr = next;
    }
    tail = head;
    if (prev != null) {
      head = prev.prev;
    }
    return this;
  }
  public List<T> toArray() {
    List<T> list = new ArrayList<>();
    Node<T> curr = head;
    while (curr != null) {
      list.add(curr.value);
      curr = curr.next;
    }
    return list;
  }
  public int size() {
    return size;
  }
  public boolean isEmpty() {
    return size == 0;
  }
}