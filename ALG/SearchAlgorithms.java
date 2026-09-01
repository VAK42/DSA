import java.util.Objects;
public class SearchAlgorithms {
  public static <T> int linearSearch(T[] array, T seekElement) {
    for (int i = 0; i < array.length; i++) {
      if (Objects.equals(array[i], seekElement)) {
        return i;
      }
    }
    return -1;
  }
  public static int binarySearch(int[] sortedArray, int seekElement) {
    int startIndex = 0;
    int endIndex = sortedArray.length - 1;
    while (startIndex <= endIndex) {
      int middleIndex = startIndex + (endIndex - startIndex) / 2;
      if (sortedArray[middleIndex] == seekElement) {
        return middleIndex;
      }
      if (sortedArray[middleIndex] < seekElement) {
        startIndex = middleIndex + 1;
      } else {
        endIndex = middleIndex - 1;
      }
    }
    return -1;
  }
  public static int jumpSearch(int[] sortedArray, int seekElement) {
    int arraySize = sortedArray.length;
    if (arraySize == 0) return -1;
    int jumpSize = (int) Math.floor(Math.sqrt(arraySize));
    int blockStart = 0;
    int blockEnd = jumpSize;
    while (blockEnd < arraySize && sortedArray[Math.min(blockEnd, arraySize) - 1] < seekElement) {
      blockStart = blockEnd;
      blockEnd += jumpSize;
    }
    for (int i = blockStart; i < Math.min(blockEnd, arraySize); i++) {
      if (sortedArray[i] == seekElement) {
        return i;
      }
    }
    return -1;
  }
  public static int interpolationSearch(int[] sortedArray, int seekElement) {
    int left = 0;
    int right = sortedArray.length - 1;
    while (left <= right && seekElement >= sortedArray[left] && seekElement <= sortedArray[right]) {
      if (left == right) {
        if (sortedArray[left] == seekElement) return left;
        return -1;
      }
      int pos = left + ((seekElement - sortedArray[left]) * (right - left)) / (sortedArray[right] - sortedArray[left]);
      if (sortedArray[pos] == seekElement) {
        return pos;
      }
      if (sortedArray[pos] < seekElement) {
        left = pos + 1;
      } else {
        right = pos - 1;
      }
    }
    return -1;
  }
}