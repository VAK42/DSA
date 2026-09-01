import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
public class PriorityQueue<T> {
  public static class Element<T> implements Comparable<Element<T>> {
    public T value;
    public int priority;
    public Element(T value, int priority) {
      this.value = value;
      this.priority = priority;
    }
    @Override
    public int compareTo(Element<T> other) {
      return Integer.compare(this.priority, other.priority);
    }
  }
  private List<Element<T>> heap;
  public PriorityQueue() {
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
  public void add(T value, int priority) {
    heap.add(new Element<>(value, priority));
    heapifyUp(heap.size() - 1);
  }
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return heap.get(0).value;
  }
  public T poll() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    T item = heap.get(0).value;
    Element<T> last = heap.remove(heap.size() - 1);
    if (!heap.isEmpty()) {
      heap.set(0, last);
      heapifyDown(0);
    }
    return item;
  }
  public boolean changePriority(T item, int priority) {
    for (int i = 0; i < heap.size(); i++) {
      if (Objects.equals(heap.get(i).value, item)) {
        int oldPriority = heap.get(i).priority;
        heap.get(i).priority = priority;
        if (priority < oldPriority) {
          heapifyUp(i);
        } else {
          heapifyDown(i);
        }
        return true;
      }
    }
    return false;
  }
  public boolean hasValue(T item) {
    for (Element<T> element : heap) {
      if (Objects.equals(element.value, item)) {
        return true;
      }
    }
    return false;
  }
  private void heapifyUp(int index) {
    while (index > 0 && heap.get(parentIndex(index)).compareTo(heap.get(index)) > 0) {
      Collections.swap(heap, parentIndex(index), index);
      index = parentIndex(index);
    }
  }
  private void heapifyDown(int index) {
    while (leftChildIndex(index) < heap.size()) {
      int smallerChildIndex = leftChildIndex(index);
      if (rightChildIndex(index) < heap.size() && heap.get(rightChildIndex(index)).compareTo(heap.get(smallerChildIndex)) < 0) {
        smallerChildIndex = rightChildIndex(index);
      }
      if (heap.get(index).compareTo(heap.get(smallerChildIndex)) <= 0) {
        break;
      }
      Collections.swap(heap, index, smallerChildIndex);
      index = smallerChildIndex;
    }
  }
  public boolean isEmpty() {
    return heap.isEmpty();
  }
  public int size() {
    return heap.size();
  }
}