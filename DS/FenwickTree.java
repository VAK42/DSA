public class FenwickTree {
  private int[] tree;
  public FenwickTree(int size) {
    this.tree = new int[size + 1];
  }
  public void increase(int position, int value) {
    if (position < 1 || position >= tree.length) {
      throw new IllegalArgumentException();
    }
    for (int i = position; i < tree.length; i += (i & -i)) {
      tree[i] += value;
    }
  }
  public int query(int position) {
    if (position < 1 || position >= tree.length) {
      throw new IllegalArgumentException();
    }
    int sum = 0;
    for (int i = position; i > 0; i -= (i & -i)) {
      sum += tree[i];
    }
    return sum;
  }
  public int queryRange(int left, int right) {
    if (left > right) {
      throw new IllegalArgumentException();
    }
    if (left == 1) {
      return query(right);
    }
    return query(right) - query(left - 1);
  }
}