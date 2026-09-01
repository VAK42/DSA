import java.util.ArrayList;
import java.util.List;
public class AVLTree<T extends Comparable<T>> {
  public static class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public int height;
    public Node(T value) {
      this.value = value;
      this.height = 1;
    }
  }
  public Node<T> root;
  public AVLTree() {
    this.root = null;
  }
  private int height(Node<T> n) {
    return n == null ? 0 : n.height;
  }
  private int getBalance(Node<T> n) {
    return n == null ? 0 : height(n.left) - height(n.right);
  }
  private void updateHeight(Node<T> n) {
    if (n != null) {
      n.height = 1 + Math.max(height(n.left), height(n.right));
    }
  }
  private Node<T> rotateRight(Node<T> y) {
    Node<T> x = y.left;
    Node<T> t2 = x.right;
    x.right = y;
    y.left = t2;
    updateHeight(y);
    updateHeight(x);
    return x;
  }
  private Node<T> rotateLeft(Node<T> x) {
    Node<T> y = x.right;
    Node<T> t2 = y.left;
    y.left = x;
    x.right = t2;
    updateHeight(x);
    updateHeight(y);
    return y;
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
    } else {
      return node;
    }
    updateHeight(node);
    int balance = getBalance(node);
    if (balance > 1 && value.compareTo(node.left.value) < 0) {
      return rotateRight(node);
    }
    if (balance < -1 && value.compareTo(node.right.value) > 0) {
      return rotateLeft(node);
    }
    if (balance > 1 && value.compareTo(node.left.value) > 0) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }
    if (balance < -1 && value.compareTo(node.right.value) < 0) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    return node;
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
      if (node.left == null || node.right == null) {
        Node<T> temp = (node.left != null) ? node.left : node.right;
        if (temp == null) {
          node = null;
        } else {
          node = temp;
        }
      } else {
        Node<T> temp = minValueNode(node.right);
        node.value = temp.value;
        node.right = remove(node.right, temp.value);
      }
    }
    if (node == null) {
      return null;
    }
    updateHeight(node);
    int balance = getBalance(node);
    if (balance > 1 && getBalance(node.left) >= 0) {
      return rotateRight(node);
    }
    if (balance > 1 && getBalance(node.left) < 0) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }
    if (balance < -1 && getBalance(node.right) <= 0) {
      return rotateLeft(node);
    }
    if (balance < -1 && getBalance(node.right) > 0) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    return node;
  }
  private Node<T> minValueNode(Node<T> node) {
    Node<T> current = node;
    while (current.left != null) {
      current = current.left;
    }
    return current;
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