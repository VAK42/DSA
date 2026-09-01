import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
public class Queue<T> {
  private static class Node<T> {
    T value;
    Node<T> next;
    Node(T value) {
      this.value = value;
      this.next = null;
    }
  }
  private Node<T> head;
  private Node<T> tail;
  private int size;
  public Queue() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  public void enqueue(T value) {
    Node<T> n = new Node<>(value);
    if (tail != null) {
      tail.next = n;
    }
    tail = n;
    if (head == null) {
      head = n;
    }
    size++;
  }
  public T dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    T val = head.value;
    head = head.next;
    if (head == null) {
      tail = null;
    }
    size--;
    return val;
  }
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return head.value;
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