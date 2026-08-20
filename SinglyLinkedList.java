public class SinglyLinkedList<T> {
  private class Node {
    T value;
    Node next;
    public Node(T value, Node next) {
      this.value = value;
      this.next = next;
    }
  }
  public Node head;
  public Node tail;
  public void add(T value) {
    Node n = new Node(value, null);
    if (head == null) {
      head = n;
      tail = n;
    } else {
      tail.next = n;
      tail = n;
    }
  }
  public void prepend(T value) {
    Node n = new Node(value, head);
    head = n;
    if (tail == null) {
      tail = n;
    }
  }
}
