import java.util.function.Consumer;
public class LinkedListAlgorithms {
  public static <T> void traversal(SinglyLinkedL<T> list, Consumer<T> callback) {
    SinglyLinkedL.Node<T> current = list.head;
    while (current != null) {
      callback.accept(current.value);
      current = current.next;
    }
  }
  public static <T> void reverseTraversal(SinglyLinkedL<T> list, Consumer<T> callback) {
    reverseTraversalRecursive(list.head, callback);
  }
  private static <T> void reverseTraversalRecursive(SinglyLinkedL.Node<T> node, Consumer<T> callback) {
    if (node != null) {
      reverseTraversalRecursive(node.next, callback);
      callback.accept(node.value);
    }
  }
}