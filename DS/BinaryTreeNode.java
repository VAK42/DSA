import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class BinaryTreeNode<T> {
  public T value;
  public BinaryTreeNode<T> left;
  public BinaryTreeNode<T> right;
  public BinaryTreeNode<T> parent;
  public BinaryTreeNode(T value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.parent = null;
  }
  public int getLeftHeight() {
    return left == null ? 0 : left.getHeight() + 1;
  }
  public int getRightHeight() {
    return right == null ? 0 : right.getHeight() + 1;
  }
  public int getHeight() {
    return Math.max(getLeftHeight(), getRightHeight());
  }
  public int getBalanceFactor() {
    return getLeftHeight() - getRightHeight();
  }
  public BinaryTreeNode<T> getUncle() {
    if (parent == null || parent.parent == null) {
      return null;
    }
    if (parent.parent.left == null || parent.parent.right == null) {
      return null;
    }
    if (Objects.equals(parent, parent.parent.left)) {
      return parent.parent.right;
    }
    return parent.parent.left;
  }
  public BinaryTreeNode<T> setValue(T value) {
    this.value = value;
    return this;
  }
  public BinaryTreeNode<T> setLeft(BinaryTreeNode<T> node) {
    if (this.left != null) {
      this.left.parent = null;
    }
    this.left = node;
    if (this.left != null) {
      this.left.parent = this;
    }
    return this;
  }
  public BinaryTreeNode<T> setRight(BinaryTreeNode<T> node) {
    if (this.right != null) {
      this.right.parent = null;
    }
    this.right = node;
    if (this.right != null) {
      this.right.parent = this;
    }
    return this;
  }
  public boolean removeChild(BinaryTreeNode<T> nodeToRemove) {
    if (left != null && Objects.equals(left, nodeToRemove)) {
      left = null;
      return true;
    }
    if (right != null && Objects.equals(right, nodeToRemove)) {
      right = null;
      return true;
    }
    return false;
  }
  public boolean replaceChild(BinaryTreeNode<T> nodeToReplace, BinaryTreeNode<T> replacementNode) {
    if (nodeToReplace == null || replacementNode == null) {
      return false;
    }
    if (left != null && Objects.equals(left, nodeToReplace)) {
      left = replacementNode;
      return true;
    }
    if (right != null && Objects.equals(right, nodeToReplace)) {
      right = replacementNode;
      return true;
    }
    return false;
  }
  public List<T> traverseInOrder() {
    List<T> traverse = new ArrayList<>();
    if (left != null) {
      traverse.addAll(left.traverseInOrder());
    }
    traverse.add(value);
    if (right != null) {
      traverse.addAll(right.traverseInOrder());
    }
    return traverse;
  }
}