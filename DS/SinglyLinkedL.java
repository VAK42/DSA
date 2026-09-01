import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class SinglyLinkedL<T> {
  public static class Node<T> {
    public T value;
    public Node<T> next;
    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
    public Node(T value) {
      this(value, null);
    }
  }
  public Node<T> head;
  public Node<T> tail;
  private int size;
  public SinglyLinkedL() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  public void add(T value) {
    append(value);
  }
  public SinglyLinkedL<T> append(T value) {
    Node<T> n = new Node<>(value);
    if (head == null) {
      head = n;
      tail = n;
    } else {
      tail.next = n;
      tail = n;
    }
    size++;
    return this;
  }
  public SinglyLinkedL<T> prepend(T value) {
    Node<T> n = new Node<>(value, head);
    head = n;
    if (tail == null) {
      tail = n;
    }
    size++;
    return this;
  }
  public SinglyLinkedL<T> insert(T value, int index) {
    if (index <= 0) {
      return prepend(value);
    }
    if (index >= size) {
      return append(value);
    }
    Node<T> curr = head;
    for (int i = 0; i < index - 1; i++) {
      curr = curr.next;
    }
    Node<T> n = new Node<>(value, curr.next);
    curr.next = n;
    size++;
    return this;
  }
  public T delete(T value) {
    if (head == null) {
      return null;
    }
    T deleted = null;
    while (head != null && Objects.equals(head.value, value)) {
      deleted = head.value;
      head = head.next;
      size--;
    }
    if (head == null) {
      tail = null;
      return deleted;
    }
    Node<T> curr = head;
    while (curr.next != null) {
      if (Objects.equals(curr.next.value, value)) {
        deleted = curr.next.value;
        curr.next = curr.next.next;
        size--;
      } else {
        curr = curr.next;
      }
    }
    tail = curr;
    return deleted;
  }
  public T deleteHead() {
    if (head == null) {
      return null;
    }
    T val = head.value;
    head = head.next;
    size--;
    if (head == null) {
      tail = null;
    }
    return val;
  }
  public T deleteTail() {
    if (head == null) {
      return null;
    }
    if (head == tail) {
      T val = head.value;
      head = null;
      tail = null;
      size = 0;
      return val;
    }
    Node<T> curr = head;
    while (curr.next != tail) {
      curr = curr.next;
    }
    T val = tail.value;
    curr.next = null;
    tail = curr;
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
  public SinglyLinkedL<T> reverse() {
    Node<T> curr = head;
    Node<T> prev = null;
    Node<T> next = null;
    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    tail = head;
    head = prev;
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