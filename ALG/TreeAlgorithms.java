import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
public class TreeAlgorithms {
  public static <T> void BFS(BinaryTreeNode<T> rootNode, Consumer<BinaryTreeNode<T>> callback) {
    if (rootNode == null) return;
    Queue<BinaryTreeNode<T>> queue = new ArrayDeque<>();
    queue.add(rootNode);
    while (!queue.isEmpty()) {
      BinaryTreeNode<T> current = queue.poll();
      callback.accept(current);
      if (current.left != null) {
        queue.add(current.left);
      }
      if (current.right != null) {
        queue.add(current.right);
      }
    }
  }
  public static <T> void DFS(BinaryTreeNode<T> rootNode, Consumer<BinaryTreeNode<T>> callback) {
    if (rootNode == null) return;
    callback.accept(rootNode);
    if (rootNode.left != null) {
      DFS(rootNode.left, callback);
    }
    if (rootNode.right != null) {
      DFS(rootNode.right, callback);
    }
  }
  public static <T> List<T> getBFSValues(BinaryTreeNode<T> rootNode) {
    List<T> values = new ArrayList<>();
    BFS(rootNode, node -> values.add(node.value));
    return values;
  }
  public static <T> List<T> getDFSValues(BinaryTreeNode<T> rootNode) {
    List<T> values = new ArrayList<>();
    DFS(rootNode, node -> values.add(node.value));
    return values;
  }
}