import java.util.ArrayList;
import java.util.List;
public class RedBlackTree<T extends Comparable<T>> {
  public static final boolean red = true;
  public static final boolean black = false;
  public static class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public boolean color;
    public Node(T value) {
      this.value = value;
      this.color = red;
      this.left = null;
      this.right = null;
      this.parent = null;
    }
  }
  public Node<T> root;
  public RedBlackTree() {
    this.root = null;
  }
  private boolean isRed(Node<T> node) {
    return node != null && node.color == red;
  }
  private boolean isBlack(Node<T> node) {
    return node == null || node.color == black;
  }
  private void rotateLeft(Node<T> node) {
    Node<T> right = node.right;
    node.right = right.left;
    if (right.left != null) {
      right.left.parent = node;
    }
    right.parent = node.parent;
    if (node.parent == null) {
      root = right;
    } else if (node == node.parent.left) {
      node.parent.left = right;
    } else {
      node.parent.right = right;
    }
    right.left = node;
    node.parent = right;
  }
  private void rotateRight(Node<T> node) {
    Node<T> left = node.left;
    node.left = left.right;
    if (left.right != null) {
      left.right.parent = node;
    }
    left.parent = node.parent;
    if (node.parent == null) {
      root = left;
    } else if (node == node.parent.right) {
      node.parent.right = left;
    } else {
      node.parent.left = left;
    }
    left.right = node;
    node.parent = left;
  }
  public void insert(T value) {
    Node<T> node = new Node<>(value);
    root = insertBst(root, node);
    fixInsert(node);
  }
  private Node<T> insertBst(Node<T> root, Node<T> node) {
    if (root == null) {
      return node;
    }
    if (node.value.compareTo(root.value) < 0) {
      root.left = insertBst(root.left, node);
      root.left.parent = root;
    } else if (node.value.compareTo(root.value) > 0) {
      root.right = insertBst(root.right, node);
      root.right.parent = root;
    }
    return root;
  }
  private void fixInsert(Node<T> node) {
    Node<T> parent = null;
    Node<T> grandParent = null;
    while (node != root && isRed(node) && isRed(node.parent)) {
      parent = node.parent;
      grandParent = parent.parent;
      if (parent == grandParent.left) {
        Node<T> uncle = grandParent.right;
        if (isRed(uncle)) {
          grandParent.color = red;
          parent.color = black;
          uncle.color = black;
          node = grandParent;
        } else {
          if (node == parent.right) {
            rotateLeft(parent);
            node = parent;
            parent = node.parent;
          }
          rotateRight(grandParent);
          boolean t = parent.color;
          parent.color = grandParent.color;
          grandParent.color = t;
          node = parent;
        }
      } else {
        Node<T> uncle = grandParent.left;
        if (isRed(uncle)) {
          grandParent.color = red;
          parent.color = black;
          uncle.color = black;
          node = grandParent;
        } else {
          if (node == parent.left) {
            rotateRight(parent);
            node = parent;
            parent = node.parent;
          }
          rotateLeft(grandParent);
          boolean t = parent.color;
          parent.color = grandParent.color;
          grandParent.color = t;
          node = parent;
        }
      }
    }
    root.color = black;
  }
  public boolean contains(T value) {
    Node<T> curr = root;
    while (curr != null) {
      int cmp = value.compareTo(curr.value);
      if (cmp == 0) return true;
      curr = cmp < 0 ? curr.left : curr.right;
    }
    return false;
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
}