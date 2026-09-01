import java.util.ArrayList;
import java.util.List;
public class BinarySearchTree<T extends Comparable<T>> {
  public static class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node(T value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }
  public Node<T> root;
  public BinarySearchTree() {
    this.root = null;
  }
  public void insert(T value) {
    root = insert(root, value);
  }
  private Node<T> insert(Node<T> node, T value) {
    if (node == null) {
      return new Node<>(value);
    }
    int cmp = value.compareTo(node.value);
    if (cmp < 0) {
      node.left = insert(node.left, value);
    } else if (cmp > 0) {
      node.right = insert(node.right, value);
    }
    return node;
  }
  public boolean contains(T value) {
    return contains(root, value);
  }
  private boolean contains(Node<T> node, T value) {
    if (node == null) {
      return false;
    }
    int cmp = value.compareTo(node.value);
    if (cmp < 0) {
      return contains(node.left, value);
    } else if (cmp > 0) {
      return contains(node.right, value);
    }
    return true;
  }
  public void remove(T value) {
    root = remove(root, value);
  }
  private Node<T> remove(Node<T> node, T value) {
    if (node == null) {
      return null;
    }
    int cmp = value.compareTo(node.value);
    if (cmp < 0) {
      node.left = remove(node.left, value);
    } else if (cmp > 0) {
      node.right = remove(node.right, value);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }
      node.value = findMin(node.right);
      node.right = remove(node.right, node.value);
    }
    return node;
  }
  public T findMin() {
    if (root == null) {
      return null;
    }
    return findMin(root);
  }
  private T findMin(Node<T> node) {
    while (node.left != null) {
      node = node.left;
    }
    return node.value;
  }
  public T findMax() {
    if (root == null) {
      return null;
    }
    return findMax(root);
  }
  private T findMax(Node<T> node) {
    while (node.right != null) {
      node = node.right;
    }
    return node.value;
  }
  public List<T> inOrder() {
    List<T> list = new ArrayList<>();
    inOrder(root, list);
    return list;
  }
  private void inOrder(Node<T> node, List<T> list) {
    if (node != null) {
      inOrder(node.left, list);
      list.add(node.value);
      inOrder(node.right, list);
    }
  }
  public List<T> preOrder() {
    List<T> list = new ArrayList<>();
    preOrder(root, list);
    return list;
  }
  private void preOrder(Node<T> node, List<T> list) {
    if (node != null) {
      list.add(node.value);
      preOrder(node.left, list);
      preOrder(node.right, list);
    }
  }
  public List<T> postOrder() {
    List<T> list = new ArrayList<>();
    postOrder(root, list);
    return list;
  }
  private void postOrder(Node<T> node, List<T> list) {
    if (node != null) {
      postOrder(node.left, list);
      postOrder(node.right, list);
      list.add(node.value);
    }
  }
}