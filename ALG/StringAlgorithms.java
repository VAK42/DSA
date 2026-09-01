import java.util.ArrayList;
import java.util.List;
public class StringAlgorithms {
  public static int hammingDistance(String a, String b) {
    if (a.length() != b.length()) {
      throw new IllegalArgumentException();
    }
    int distance = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        distance++;
      }
    }
    return distance;
  }
  public static boolean isPalindrome(String string) {
    int left = 0;
    int right = string.length() - 1;
    while (left < right) {
      if (string.charAt(left) != string.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
  public static int levenshteinDistance(String a, String b) {
    int[][] distanceMatrix = new int[b.length() + 1][a.length() + 1];
    for (int i = 0; i <= a.length(); i++) {
      distanceMatrix[0][i] = i;
    }
    for (int j = 0; j <= b.length(); j++) {
      distanceMatrix[j][0] = j;
    }
    for (int j = 1; j <= b.length(); j++) {
      for (int i = 1; i <= a.length(); i++) {
        int indicator = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
        distanceMatrix[j][i] = Math.min(
          Math.min(distanceMatrix[j][i - 1] + 1, distanceMatrix[j - 1][i] + 1),
          distanceMatrix[j - 1][i - 1] + indicator
        );
      }
    }
    return distanceMatrix[b.length()][a.length()];
  }
  public static int[] buildKMPTable(String word) {
    int[] patternTable = new int[word.length()];
    int prefixIndex = 0;
    int suffixIndex = 1;
    while (suffixIndex < word.length()) {
      if (word.charAt(prefixIndex) == word.charAt(suffixIndex)) {
        patternTable[suffixIndex] = prefixIndex + 1;
        prefixIndex++;
        suffixIndex++;
      } else if (prefixIndex != 0) {
        prefixIndex = patternTable[prefixIndex - 1];
      } else {
        patternTable[suffixIndex] = 0;
        suffixIndex++;
      }
    }
    return patternTable;
  }
  public static int KMP(String text, String word) {
    if (word.isEmpty()) return 0;
    int[] patternTable = buildKMPTable(word);
    int textIndex = 0;
    int wordIndex = 0;
    while (textIndex < text.length()) {
      if (text.charAt(textIndex) == word.charAt(wordIndex)) {
        if (wordIndex == word.length() - 1) {
          return textIndex - word.length() + 1;
        }
        wordIndex++;
        textIndex++;
      } else if (wordIndex > 0) {
        wordIndex = patternTable[wordIndex - 1];
      } else {
        textIndex++;
      }
    }
    return -1;
  }
  public static int[] ZAlgorithm(String text) {
    int n = text.length();
    int[] z = new int[n];
    int left = 0;
    int right = 0;
    for (int i = 1; i < n; i++) {
      if (i > right) {
        left = right = i;
        while (right < n && text.charAt(right) == text.charAt(right - left)) {
          right++;
        }
        z[i] = right - left;
        right--;
      } else {
        int k = i - left;
        if (z[k] < right - i + 1) {
          z[i] = z[k];
        } else {
          left = i;
          while (right < n && text.charAt(right) == text.charAt(right - left)) {
            right++;
          }
          z[i] = right - left;
          right--;
        }
      }
    }
    return z;
  }
  public static int rabinKarp(String text, String word) {
    if (word.isEmpty()) return 0;
    if (text.length() < word.length()) return -1;
    int base = 256;
    int prime = 101;
    int m = word.length();
    int n = text.length();
    int p = 0;
    int t = 0;
    int h = 1;
    for (int i = 0; i < m - 1; i++) {
      h = (h * base) % prime;
    }
    for (int i = 0; i < m; i++) {
      p = (base * p + word.charAt(i)) % prime;
      t = (base * t + text.charAt(i)) % prime;
    }
    for (int i = 0; i <= n - m; i++) {
      if (p == t) {
        boolean match = true;
        for (int j = 0; j < m; j++) {
          if (text.charAt(i + j) != word.charAt(j)) {
            match = false;
            break;
          }
        }
        if (match) return i;
      }
      if (i < n - m) {
        t = (base * (t - text.charAt(i) * h) + text.charAt(i + m)) % prime;
        if (t < 0) t += prime;
      }
    }
    return -1;
  }
  public static String longestCommonSubstring(String string1, String string2) {
    int[][] lcsMatrix = new int[string2.length() + 1][string1.length() + 1];
    int longestLength = 0;
    int longestColumn = 0;
    for (int j = 1; j <= string2.length(); j++) {
      for (int i = 1; i <= string1.length(); i++) {
        if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
          lcsMatrix[j][i] = lcsMatrix[j - 1][i - 1] + 1;
          if (lcsMatrix[j][i] > longestLength) {
            longestLength = lcsMatrix[j][i];
            longestColumn = i;
          }
        } else {
          lcsMatrix[j][i] = 0;
        }
      }
    }
    if (longestLength == 0) return "";
    return string1.substring(longestColumn - longestLength, longestColumn);
  }
  public static boolean regularExpressionMatch(String s, String p) {
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    dp[0][0] = true;
    for (int j = 1; j <= p.length(); j++) {
      if (p.charAt(j - 1) == '*') {
        dp[0][j] = dp[0][j - 2];
      }
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= p.length(); j++) {
        if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i][j - 2];
          if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
            dp[i][j] = dp[i][j] || dp[i - 1][j];
          }
        }
      }
    }
    return dp[s.length()][p.length()];
  }
}