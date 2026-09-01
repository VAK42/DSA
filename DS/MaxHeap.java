import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
public class MaxHeap<T extends Comparable<T>> {
  private List<T> heap;
  public MaxHeap() {
    heap = new ArrayList<>();
  }
  private int parentIndex(int i) {
    return (i - 1) / 2;
  }
  private int leftChildIndex(int i) {
    return 2 * i + 1;
  }
  private int rightChildIndex(int i) {
    return 2 * i + 2;
  }
  private boolean hasParent(int i) {
    return i > 0;
  }
  private boolean hasLeftChild(int i) {
    return leftChildIndex(i) < heap.size();
  }
  private boolean hasRightChild(int i) {
    return rightChildIndex(i) < heap.size();
  }
  public void add(T value) {
    heap.add(value);
    heapifyUp();
  }
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return heap.get(0);
  }
  public T poll() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    T item = heap.get(0);
    T last = heap.remove(heap.size() - 1);
    if (!heap.isEmpty()) {
      heap.set(0, last);
      heapifyDown();
    }
    return item;
  }
  public boolean remove(T item) {
    int index = -1;
    for (int i = 0; i < heap.size(); i++) {
      if (Objects.equals(heap.get(i), item)) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      return false;
    }
    if (index == heap.size() - 1) {
      heap.remove(heap.size() - 1);
      return true;
    }
    heap.set(index, heap.remove(heap.size() - 1));
    heapifyDown(index);
    heapifyUp(index);
    return true;
  }
  private void heapifyUp() {
    heapifyUp(heap.size() - 1);
  }
  private void heapifyUp(int index) {
    while (hasParent(index) && heap.get(parentIndex(index)).compareTo(heap.get(index)) < 0) {
      Collections.swap(heap, parentIndex(index), index);
      index = parentIndex(index);
    }
  }
  private void heapifyDown() {
    heapifyDown(0);
  }
  private void heapifyDown(int index) {
    while (hasLeftChild(index)) {
      int largerChildIndex = leftChildIndex(index);
      if (hasRightChild(index) && heap.get(rightChildIndex(index)).compareTo(heap.get(largerChildIndex)) > 0) {
        largerChildIndex = rightChildIndex(index);
      }
      if (heap.get(index).compareTo(heap.get(largerChildIndex)) >= 0) {
        break;
      }
      Collections.swap(heap, index, largerChildIndex);
      index = largerChildIndex;
    }
  }
  public boolean isEmpty() {
    return heap.isEmpty();
  }
  public int size() {
    return heap.size();
  }
  public List<T> toArray() {
    return new ArrayList<>(heap);
  }
}