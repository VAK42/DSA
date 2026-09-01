import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
public class ClassicAlgorithms {
  public static void towerOfHanoi(int n, String fromRod, String toRod, String auxRod, List<String> moves) {
    if (n == 1) {
      moves.add("Move disk 1 from " + fromRod + " to " + toRod);
      return;
    }
    towerOfHanoi(n - 1, fromRod, auxRod, toRod, moves);
    moves.add("Move disk " + n + " from " + fromRod + " to " + toRod);
    towerOfHanoi(n - 1, auxRod, toRod, fromRod, moves);
  }
  public static void rotateSquareMatrix(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n - 1 - j];
        matrix[i][n - 1 - j] = temp;
      }
    }
  }
  public static boolean canJumpGameGreedy(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > maxReach) return false;
      maxReach = Math.max(maxReach, i + nums[i]);
    }
    return true;
  }
  public static boolean canJumpGameBacktracking(int[] nums) {
    return jumpGameBacktrackingHelper(0, nums);
  }
  private static boolean jumpGameBacktrackingHelper(int position, int[] nums) {
    if (position == nums.length - 1) return true;
    int furthestJump = Math.min(position + nums[position], nums.length - 1);
    for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
      if (jumpGameBacktrackingHelper(nextPosition, nums)) return true;
    }
    return false;
  }
  public static boolean canJumpGameDP(int[] nums) {
    boolean[] dp = new boolean[nums.length];
    dp[nums.length - 1] = true;
    for (int i = nums.length - 2; i >= 0; i--) {
      int furthest = Math.min(i + nums[i], nums.length - 1);
      for (int j = i + 1; j <= furthest; j++) {
        if (dp[j]) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[0];
  }
  public static int uniquePathsDP(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }
  public static int uniquePathsBT(int width, int height) {
    return uniquePathsBtHelper(width, height, 1, 1);
  }
  private static int uniquePathsBtHelper(int width, int height, int row, int col) {
    if (row == height && col == width) return 1;
    if (row > height || col > width) return 0;
    return uniquePathsBtHelper(width, height, row + 1, col) + uniquePathsBtHelper(width, height, row, col + 1);
  }
  public static int uniquePathsPascal(int width, int height) {
    List<List<Integer>> triangle = MathAlgorithms.pascalTriangle(width + height - 1);
    return triangle.get(width + height - 2).get(Math.min(width, height) - 1);
  }
  public static int trapRainWaterDP(int[] height) {
    if (height == null || height.length == 0) return 0;
    int n = height.length;
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];
    leftMax[0] = height[0];
    for (int i = 1; i < n; i++) leftMax[i] = Math.max(leftMax[i - 1], height[i]);
    rightMax[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], height[i]);
    int water = 0;
    for (int i = 0; i < n; i++) water += Math.min(leftMax[i], rightMax[i]) - height[i];
    return water;
  }
  public static int trapRainWaterBF(int[] height) {
    if (height == null || height.length == 0) return 0;
    int water = 0;
    for (int i = 0; i < height.length; i++) {
      int maxLeft = 0;
      int maxRight = 0;
      for (int j = i; j >= 0; j--) maxLeft = Math.max(maxLeft, height[j]);
      for (int j = i; j < height.length; j++) maxRight = Math.max(maxRight, height[j]);
      water += Math.min(maxLeft, maxRight) - height[i];
    }
    return water;
  }
  public static int recursiveStaircaseBF(int stairsNum) {
    if (stairsNum <= 0) return 0;
    if (stairsNum == 1) return 1;
    if (stairsNum == 2) return 2;
    return recursiveStaircaseBF(stairsNum - 1) + recursiveStaircaseBF(stairsNum - 2);
  }
  public static int recursiveStaircaseDP(int stairsNum) {
    if (stairsNum <= 0) return 0;
    int[] dp = new int[stairsNum + 1];
    dp[0] = 0;
    dp[1] = 1;
    if (stairsNum >= 2) dp[2] = 2;
    for (int i = 3; i <= stairsNum; i++) dp[i] = dp[i - 1] + dp[i - 2];
    return dp[stairsNum];
  }
  public static int recursiveStaircaseIT(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    if (n == 2) return 2;
    int a = 1, b = 2;
    for (int i = 3; i <= n; i++) {
      int c = a + b;
      a = b;
      b = c;
    }
    return b;
  }
  public static int recursiveStaircaseMEM(int stairsNum, int[] memo) {
    if (stairsNum <= 0) return 0;
    if (stairsNum == 1) return 1;
    if (stairsNum == 2) return 2;
    if (memo[stairsNum] != 0) return memo[stairsNum];
    memo[stairsNum] = recursiveStaircaseMEM(stairsNum - 1, memo) + recursiveStaircaseMEM(stairsNum - 2, memo);
    return memo[stairsNum];
  }
  public static int maxProfitStocksAccumulator(int[] prices) {
    int totalProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        totalProfit += prices[i] - prices[i - 1];
      }
    }
    return totalProfit;
  }
  public static int maxProfitStocksDP(int[] prices) {
    if (prices == null || prices.length == 0) return 0;
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    for (int price : prices) {
      if (price < minPrice) {
        minPrice = price;
      } else if (price - minPrice > maxProfit) {
        maxProfit = price - minPrice;
      }
    }
    return maxProfit;
  }
  public static int maxProfitStocksPeakValley(int[] prices) {
    int maxProfit = 0;
    int i = 0;
    while (i < prices.length - 1) {
      while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
      int valley = prices[i];
      while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
      int peak = prices[i];
      maxProfit += peak - valley;
    }
    return maxProfit;
  }
  public static boolean isValidParentheses(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else {
        if (stack.isEmpty()) return false;
        char top = stack.pop();
        if (c == ')' && top != '(') return false;
        if (c == '}' && top != '{') return false;
        if (c == ']' && top != '[') return false;
      }
    }
    return stack.isEmpty();
  }
  public static List<List<String>> solveNQueens(int n) {
    List<List<String>> solutions = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) Arrays.fill(row, '.');
    solveNQueensHelper(0, board, solutions, n);
    return solutions;
  }
  private static void solveNQueensHelper(int col, char[][] board, List<List<String>> solutions, int n) {
    if (col == n) {
      List<String> solution = new ArrayList<>();
      for (char[] row : board) solution.add(new String(row));
      solutions.add(solution);
      return;
    }
    for (int row = 0; row < n; row++) {
      if (isSafeQueen(board, row, col, n)) {
        board[row][col] = 'Q';
        solveNQueensHelper(col + 1, board, solutions, n);
        board[row][col] = '.';
      }
    }
  }
  private static boolean isSafeQueen(char[][] board, int row, int col, int n) {
    for (int j = 0; j < col; j++) {
      if (board[row][j] == 'Q') return false;
    }
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') return false;
    }
    for (int i = row, j = col; i < n && j >= 0; i++, j--) {
      if (board[i][j] == 'Q') return false;
    }
    return true;
  }
  public static int nQueensBitwise(int n) {
    return nQueensBitwiseHelper(0, 0, 0, (1 << n) - 1);
  }
  private static int nQueensBitwiseHelper(int leftDiagonal, int column, int rightDiagonal, int allQueens) {
    if (column == allQueens) return 1;
    int solutions = 0;
    int validPositions = ~(leftDiagonal | column | rightDiagonal) & allQueens;
    while (validPositions != 0) {
      int currentPosition = -validPositions & validPositions;
      validPositions -= currentPosition;
      solutions += nQueensBitwiseHelper(
        (leftDiagonal | currentPosition) >> 1,
        column | currentPosition,
        (rightDiagonal | currentPosition) << 1,
        allQueens
      );
    }
    return solutions;
  }
  public static int[][] knightTour(int n) {
    int[][] board = new int[n][n];
    for (int[] row : board) Arrays.fill(row, -1);
    int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};
    board[0][0] = 0;
    if (knightTourHelper(0, 0, 1, board, moveX, moveY, n)) {
      return board;
    }
    return null;
  }
  private static boolean knightTourHelper(int x, int y, int step, int[][] board, int[] moveX, int[] moveY, int n) {
    if (step == n * n) return true;
    for (int k = 0; k < 8; k++) {
      int nextX = x + moveX[k];
      int nextY = y + moveY[k];
      if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && board[nextX][nextY] == -1) {
        board[nextX][nextY] = step;
        if (knightTourHelper(nextX, nextY, step + 1, board, moveX, moveY, n)) {
          return true;
        }
        board[nextX][nextY] = -1;
      }
    }
    return false;
  }
  public static String caesarCipher(String text, int shift) {
    StringBuilder result = new StringBuilder();
    for (char character : text.toCharArray()) {
      if (Character.isLetter(character)) {
        char base = Character.isLowerCase(character) ? 'a' : 'A';
        result.append((char) ((character - base + shift + 26) % 26 + base));
      } else {
        result.append(character);
      }
    }
    return result.toString();
  }
  public static String railFenceCipherEncode(String text, int rails) {
    if (rails <= 1) return text;
    StringBuilder[] fence = new StringBuilder[rails];
    for (int i = 0; i < rails; i++) fence[i] = new StringBuilder();
    int rail = 0;
    int direction = 1;
    for (char c : text.toCharArray()) {
      fence[rail].append(c);
      rail += direction;
      if (rail == 0 || rail == rails - 1) direction = -direction;
    }
    StringBuilder result = new StringBuilder();
    for (StringBuilder sb : fence) result.append(sb);
    return result.toString();
  }
  public static long polynomialHash(String word, int prime, int base) {
    long hash = 0;
    for (int i = 0; i < word.length(); i++) {
      hash = (hash * base + word.charAt(i)) % prime;
    }
    return hash;
  }
  public static String hillCipherEncrypt(String message, String keyString) {
    int mLen = message.length();
    int kLen = (int) Math.sqrt(keyString.length());
    if (kLen * kLen != keyString.length() || kLen != mLen) {
      throw new IllegalArgumentException();
    }
    int[][] keyMatrix = new int[kLen][kLen];
    int idx = 0;
    for (int i = 0; i < kLen; i++) {
      for (int j = 0; j < kLen; j++) {
        keyMatrix[i][j] = (keyString.charAt(idx++) - 'A') % 26;
      }
    }
    int[] messageVector = new int[mLen];
    for (int i = 0; i < mLen; i++) {
      messageVector[i] = (message.charAt(i) - 'A') % 26;
    }
    StringBuilder cipher = new StringBuilder();
    for (int i = 0; i < kLen; i++) {
      int sum = 0;
      for (int j = 0; j < kLen; j++) {
        sum += keyMatrix[i][j] * messageVector[j];
      }
      cipher.append((char) ((sum % 26) + 'A'));
    }
    return cipher.toString();
  }
  public static double[][] calculateEnergyMap(int[][] image) {
    int h = image.length;
    int w = image[0].length;
    double[][] energy = new double[h][w];
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int left = x > 0 ? image[y][x - 1] : image[y][x];
        int right = x < w - 1 ? image[y][x + 1] : image[y][x];
        int top = y > 0 ? image[y - 1][x] : image[y][x];
        int bottom = y < h - 1 ? image[y + 1][x] : image[y][x];
        double dx = right - left;
        double dy = bottom - top;
        energy[y][x] = Math.sqrt(dx * dx + dy * dy);
      }
    }
    return energy;
  }
}