import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
public class Deque<T> {
  private static class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev;
    Node(T value, Node<T> next, Node<T> prev) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
  }
  private Node<T> head;
  private Node<T> tail;
  private int size;
  public Deque() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  public void addFirst(T value) {
    Node<T> n = new Node<>(value, head, null);
    if (head != null) {
      head.prev = n;
    }
    head = n;
    if (tail == null) {
      tail = n;
    }
    size++;
  }
  public void addLast(T value) {
    Node<T> n = new Node<>(value, null, tail);
    if (tail != null) {
      tail.next = n;
    }
    tail = n;
    if (head == null) {
      head = n;
    }
    size++;
  }
  public T removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
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
  public T removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
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
  public T peekFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return head.value;
  }
  public T peekLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return tail.value;
  }
  public boolean isEmpty() {
    return size == 0;
  }
  public int size() {
    return size;
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
}