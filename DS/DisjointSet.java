import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class DisjointSet<T> {
  private static class Node<T> {
    T value;
    Node<T> parent;
    int rank;
    Node(T value) {
      this.value = value;
      this.parent = this;
      this.rank = 0;
    }
  }
  private Map<T, Node<T>> nodes;
  public DisjointSet() {
    this.nodes = new HashMap<>();
  }
  public void makeSet(T value) {
    if (!nodes.containsKey(value)) {
      nodes.put(value, new Node<>(value));
    }
  }
  private Node<T> find(Node<T> node) {
    if (node.parent != node) {
      node.parent = find(node.parent);
    }
    return node.parent;
  }
  public T find(T value) {
    Node<T> node = nodes.get(value);
    if (node == null) {
      return null;
    }
    return find(node).value;
  }
  public void union(T valueA, T valueB) {
    makeSet(valueA);
    makeSet(valueB);
    Node<T> rootA = find(nodes.get(valueA));
    Node<T> rootB = find(nodes.get(valueB));
    if (rootA == rootB) {
      return;
    }
    if (rootA.rank < rootB.rank) {
      rootA.parent = rootB;
    } else if (rootA.rank > rootB.rank) {
      rootB.parent = rootA;
    } else {
      rootB.parent = rootA;
      rootA.rank++;
    }
  }
  public boolean connected(T valueA, T valueB) {
    T rootA = find(valueA);
    T rootB = find(valueB);
    if (rootA == null || rootB == null) {
      return false;
    }
    return Objects.equals(rootA, rootB);
  }
}