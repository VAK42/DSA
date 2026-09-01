import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
public class Stack<T> {
  private static class Node<T> {
    T value;
    Node<T> next;
    Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
  private Node<T> top;
  private int size;
  public Stack() {
    this.top = null;
    this.size = 0;
  }
  public void push(T value) {
    top = new Node<>(value, top);
    size++;
  }
  public T pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    T val = top.value;
    top = top.next;
    size--;
    return val;
  }
  public T peek() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return top.value;
  }
  public boolean isEmpty() {
    return size == 0;
  }
  public int size() {
    return size;
  }
  public List<T> toArray() {
    List<T> list = new ArrayList<>();
    Node<T> curr = top;
    while (curr != null) {
      list.add(curr.value);
      curr = curr.next;
    }
    return list;
  }
}