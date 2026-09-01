import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
public class SetAlgorithms {
  public static <T> List<List<T>> cartesianProduct(List<List<T>> sets) {
    List<List<T>> result = new ArrayList<>();
    if (sets == null || sets.isEmpty()) return result;
    cartesianHelper(sets, 0, new ArrayList<>(), result);
    return result;
  }
  private static <T> void cartesianHelper(List<List<T>> sets, int index, List<T> current, List<List<T>> result) {
    if (index == sets.size()) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (T item : sets.get(index)) {
      current.add(item);
      cartesianHelper(sets, index + 1, current, result);
      current.remove(current.size() - 1);
    }
  }
  public static <T> void fisherYatesShuffle(List<T> array) {
    Random random = new Random();
    for (int i = array.size() - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      Collections.swap(array, i, j);
    }
  }
  public static <T> List<List<T>> powerSetBitwise(List<T> originalSet) {
    List<List<T>> subSets = new ArrayList<>();
    int numberOfCombinations = 1 << originalSet.size();
    for (int combinationIndex = 0; combinationIndex < numberOfCombinations; combinationIndex++) {
      List<T> subSet = new ArrayList<>();
      for (int setElementIndex = 0; setElementIndex < originalSet.size(); setElementIndex++) {
        if ((combinationIndex & (1 << setElementIndex)) != 0) {
          subSet.add(originalSet.get(setElementIndex));
        }
      }
      subSets.add(subSet);
    }
    return subSets;
  }
  public static <T> List<List<T>> powerSetBacktracking(List<T> originalSet) {
    List<List<T>> subSets = new ArrayList<>();
    powerSetBacktrackingHelper(originalSet, 0, new ArrayList<>(), subSets);
    return subSets;
  }
  private static <T> void powerSetBacktrackingHelper(List<T> originalSet, int index, List<T> current, List<List<T>> subSets) {
    subSets.add(new ArrayList<>(current));
    for (int i = index; i < originalSet.size(); i++) {
      current.add(originalSet.get(i));
      powerSetBacktrackingHelper(originalSet, i + 1, current, subSets);
      current.remove(current.size() - 1);
    }
  }
  public static <T> List<List<T>> powerSetCascading(List<T> originalSet) {
    List<List<T>> sets = new ArrayList<>();
    sets.add(new ArrayList<>());
    for (T item : originalSet) {
      int currentSize = sets.size();
      for (int i = 0; i < currentSize; i++) {
        List<T> newSet = new ArrayList<>(sets.get(i));
        newSet.add(item);
        sets.add(newSet);
      }
    }
    return sets;
  }
  public static <T> List<List<T>> permutationsWithoutRepetitions(List<T> originalSet) {
    List<List<T>> permutations = new ArrayList<>();
    if (originalSet.size() <= 1) {
      permutations.add(new ArrayList<>(originalSet));
      return permutations;
    }
    for (int i = 0; i < originalSet.size(); i++) {
      T currentOption = originalSet.get(i);
      List<T> smallerSet = new ArrayList<>(originalSet);
      smallerSet.remove(i);
      List<List<T>> smallerPermutations = permutationsWithoutRepetitions(smallerSet);
      for (List<T> perm : smallerPermutations) {
        List<T> fullPerm = new ArrayList<>();
        fullPerm.add(currentOption);
        fullPerm.addAll(perm);
        permutations.add(fullPerm);
      }
    }
    return permutations;
  }
  public static <T> List<List<T>> permutationsWithRepetitions(List<T> permutationOptions, int permutationLength) {
    List<List<T>> permutations = new ArrayList<>();
    if (permutationLength == 1) {
      for (T option : permutationOptions) {
        permutations.add(Collections.singletonList(option));
      }
      return permutations;
    }
    List<List<T>> smallerPermutations = permutationsWithRepetitions(permutationOptions, permutationLength - 1);
    for (T currentOption : permutationOptions) {
      for (List<T> smallerPermutation : smallerPermutations) {
        List<T> perm = new ArrayList<>();
        perm.add(currentOption);
        perm.addAll(smallerPermutation);
        permutations.add(perm);
      }
    }
    return permutations;
  }
  public static <T> List<List<T>> combinationsWithoutRepetitions(List<T> comboOptions, int comboLength) {
    List<List<T>> combos = new ArrayList<>();
    if (comboLength == 1) {
      for (T option : comboOptions) {
        combos.add(Collections.singletonList(option));
      }
      return combos;
    }
    for (int i = 0; i <= comboOptions.size() - comboLength; i++) {
      T head = comboOptions.get(i);
      List<T> tail = comboOptions.subList(i + 1, comboOptions.size());
      List<List<T>> tailCombos = combinationsWithoutRepetitions(tail, comboLength - 1);
      for (List<T> tailCombo : tailCombos) {
        List<T> combo = new ArrayList<>();
        combo.add(head);
        combo.addAll(tailCombo);
        combos.add(combo);
      }
    }
    return combos;
  }
  public static <T> List<List<T>> combinationsWithRepetitions(List<T> comboOptions, int comboLength) {
    List<List<T>> combos = new ArrayList<>();
    if (comboLength == 1) {
      for (T option : comboOptions) {
        combos.add(Collections.singletonList(option));
      }
      return combos;
    }
    for (int i = 0; i < comboOptions.size(); i++) {
      T head = comboOptions.get(i);
      List<T> tail = comboOptions.subList(i, comboOptions.size());
      List<List<T>> tailCombos = combinationsWithRepetitions(tail, comboLength - 1);
      for (List<T> tailCombo : tailCombos) {
        List<T> combo = new ArrayList<>();
        combo.add(head);
        combo.addAll(tailCombo);
        combos.add(combo);
      }
    }
    return combos;
  }
  public static String LCS(String set1, String set2) {
    int m = set1.length();
    int n = set2.length();
    int[][] lcsMatrix = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (set1.charAt(i - 1) == set2.charAt(j - 1)) {
          lcsMatrix[i][j] = lcsMatrix[i - 1][j - 1] + 1;
        } else {
          lcsMatrix[i][j] = Math.max(lcsMatrix[i - 1][j], lcsMatrix[i][j - 1]);
        }
      }
    }
    StringBuilder lcs = new StringBuilder();
    int i = m, j = n;
    while (i > 0 && j > 0) {
      if (set1.charAt(i - 1) == set2.charAt(j - 1)) {
        lcs.append(set1.charAt(i - 1));
        i--;
        j--;
      } else if (lcsMatrix[i - 1][j] > lcsMatrix[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    return lcs.reverse().toString();
  }
  public static int LIS(int[] sequence) {
    if (sequence == null || sequence.length == 0) return 0;
    int[] lengths = new int[sequence.length];
    Arrays.fill(lengths, 1);
    int maxLen = 1;
    for (int i = 1; i < sequence.length; i++) {
      for (int j = 0; j < i; j++) {
        if (sequence[i] > sequence[j] && lengths[i] < lengths[j] + 1) {
          lengths[i] = lengths[j] + 1;
          maxLen = Math.max(maxLen, lengths[i]);
        }
      }
    }
    return maxLen;
  }
  public static String SCS(String str1, String str2) {
    String lcs = LCS(str1, str2);
    StringBuilder scs = new StringBuilder();
    int p1 = 0, p2 = 0;
    for (int i = 0; i < lcs.length(); i++) {
      char c = lcs.charAt(i);
      while (p1 < str1.length() && str1.charAt(p1) != c) {
        scs.append(str1.charAt(p1++));
      }
      while (p2 < str2.length() && str2.charAt(p2) != c) {
        scs.append(str2.charAt(p2++));
      }
      scs.append(c);
      p1++;
      p2++;
    }
    while (p1 < str1.length()) scs.append(str1.charAt(p1++));
    while (p2 < str2.length()) scs.append(str2.charAt(p2++));
    return scs.toString();
  }
  public static int knapsack01(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][capacity + 1];
    for (int i = 1; i <= n; i++) {
      for (int w = 1; w <= capacity; w++) {
        if (weights[i - 1] <= w) {
          dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }
    return dp[n][capacity];
  }
  public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];
    for (int w = 0; w <= capacity; w++) {
      for (int i = 0; i < weights.length; i++) {
        if (weights[i] <= w) {
          dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
        }
      }
    }
    return dp[capacity];
  }
  public static int maxSubArrayBruteForce(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum > max) max = sum;
      }
    }
    return max;
  }
  public static int maxSubArrayKadane(int[] nums) {
    int maxSoFar = nums[0];
    int maxEndingHere = nums[0];
    for (int i = 1; i < nums.length; i++) {
      maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
  }
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    combinationSumHelper(candidates, target, 0, new ArrayList<>(), result);
    return result;
  }
  private static void combinationSumHelper(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int i = start; i < candidates.length; i++) {
      if (candidates[i] > target) break;
      current.add(candidates[i]);
      combinationSumHelper(candidates, target - candidates[i], i, current, result);
      current.remove(current.size() - 1);
    }
  }
}