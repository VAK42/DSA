import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class SortAlgorithms {
  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    boolean swapped;
    for (int i = 0; i < n - 1; i++) {
      swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped) break;
    }
  }
  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int minIdx = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[minIdx]) {
          minIdx = j;
        }
      }
      int temp = arr[minIdx];
      arr[minIdx] = arr[i];
      arr[i] = temp;
    }
  }
  public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }
  public static void heapSort(int[] arr) {
    int n = arr.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(arr, n, i);
    }
    for (int i = n - 1; i > 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      heapify(arr, i, 0);
    }
  }
  private static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    if (left < n && arr[left] > arr[largest]) {
      largest = left;
    }
    if (right < n && arr[right] > arr[largest]) {
      largest = right;
    }
    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;
      heapify(arr, n, largest);
    }
  }
  public static void mergeSort(int[] arr) {
    if (arr.length < 2) return;
    int mid = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);
    mergeSort(left);
    mergeSort(right);
    merge(arr, left, right);
  }
  private static void merge(int[] arr, int[] left, int[] right) {
    int i = 0, j = 0, k = 0;
    while (i < left.length && j < right.length) {
      if (left[i] <= right[j]) {
        arr[k++] = left[i++];
      } else {
        arr[k++] = right[j++];
      }
    }
    while (i < left.length) arr[k++] = left[i++];
    while (j < right.length) arr[k++] = right[j++];
  }
  public static void quickSort(int[] arr) {
    quickSortHelper(arr, 0, arr.length - 1);
  }
  private static void quickSortHelper(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSortHelper(arr, low, pi - 1);
      quickSortHelper(arr, pi + 1, high);
    }
  }
  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
  }
  public static void shellSort(int[] arr) {
    int n = arr.length;
    for (int gap = n / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < n; i++) {
        int temp = arr[i];
        int j;
        for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
          arr[j] = arr[j - gap];
        }
        arr[j] = temp;
      }
    }
  }
  public static void countingSort(int[] arr) {
    if (arr.length == 0) return;
    int max = Arrays.stream(arr).max().getAsInt();
    int min = Arrays.stream(arr).min().getAsInt();
    int range = max - min + 1;
    int[] count = new int[range];
    int[] output = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      count[arr[i] - min]++;
    }
    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }
    for (int i = arr.length - 1; i >= 0; i--) {
      output[count[arr[i] - min] - 1] = arr[i];
      count[arr[i] - min]--;
    }
    System.arraycopy(output, 0, arr, 0, arr.length);
  }
  public static void radixSort(int[] arr) {
    if (arr.length == 0) return;
    int max = Arrays.stream(arr).max().getAsInt();
    for (int exp = 1; max / exp > 0; exp *= 10) {
      countSortByDigit(arr, exp);
    }
  }
  private static void countSortByDigit(int[] arr, int exp) {
    int n = arr.length;
    int[] output = new int[n];
    int[] count = new int[10];
    for (int i = 0; i < n; i++) {
      count[(arr[i] / exp) % 10]++;
    }
    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }
    for (int i = n - 1; i >= 0; i--) {
      output[count[(arr[i] / exp) % 10] - 1] = arr[i];
      count[(arr[i] / exp) % 10]--;
    }
    System.arraycopy(output, 0, arr, 0, n);
  }
  @SuppressWarnings("unchecked")
  public static void bucketSort(float[] arr) {
    int n = arr.length;
    if (n <= 0) return;
    List<Float>[] buckets = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      buckets[i] = new ArrayList<>();
    }
    for (int i = 0; i < n; i++) {
      int bucketIdx = (int) (arr[i] * n);
      if (bucketIdx >= n) bucketIdx = n - 1;
      buckets[bucketIdx].add(arr[i]);
    }
    for (int i = 0; i < n; i++) {
      Collections.sort(buckets[i]);
    }
    int index = 0;
    for (int i = 0; i < n; i++) {
      for (float val : buckets[i]) {
        arr[index++] = val;
      }
    }
  }
}