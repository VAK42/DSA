public class SegmentTree {
  private int[] tree;
  private int n;
  public SegmentTree(int[] inputArray) {
    this.n = inputArray.length;
    this.tree = new int[4 * n];
    if (n > 0) {
      buildTree(inputArray, 0, 0, n - 1);
    }
  }
  private void buildTree(int[] arr, int treeIndex, int l, int r) {
    if (l == r) {
      tree[treeIndex] = arr[l];
      return;
    }
    int mid = l + (r - l) / 2;
    int leftChild = 2 * treeIndex + 1;
    int rightChild = 2 * treeIndex + 2;
    buildTree(arr, leftChild, l, mid);
    buildTree(arr, rightChild, mid + 1, r);
    tree[treeIndex] = tree[leftChild] + tree[rightChild];
  }
  public int rangeSumQuery(int queryL, int queryR) {
    return rangeSumQuery(0, 0, n - 1, queryL, queryR);
  }
  private int rangeSumQuery(int treeIndex, int l, int r, int queryL, int queryR) {
    if (queryL <= l && r <= queryR) {
      return tree[treeIndex];
    }
    if (queryR < l || queryL > r) {
      return 0;
    }
    int mid = l + (r - l) / 2;
    int leftChild = 2 * treeIndex + 1;
    int rightChild = 2 * treeIndex + 2;
    return rangeSumQuery(leftChild, l, mid, queryL, queryR) + rangeSumQuery(rightChild, mid + 1, r, queryL, queryR);
  }
  public void update(int index, int value) {
    update(0, 0, n - 1, index, value);
  }
  private void update(int treeIndex, int l, int r, int index, int value) {
    if (l == r) {
      tree[treeIndex] = value;
      return;
    }
    int mid = l + (r - l) / 2;
    int leftChild = 2 * treeIndex + 1;
    int rightChild = 2 * treeIndex + 2;
    if (index <= mid) {
      update(leftChild, l, mid, index, value);
    } else {
      update(rightChild, mid + 1, r, index, value);
    }
    tree[treeIndex] = tree[leftChild] + tree[rightChild];
  }
}