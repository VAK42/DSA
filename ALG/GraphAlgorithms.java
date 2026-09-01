import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
public class GraphAlgorithms {
  public static List<String> BFS(Graph graph, String startVertexKey) {
    List<String> visitedOrder = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    java.util.Queue<String> queue = new ArrayDeque<>();
    queue.add(startVertexKey);
    visited.add(startVertexKey);
    while (!queue.isEmpty()) {
      String current = queue.poll();
      visitedOrder.add(current);
      for (Graph.Vertex neighbor : graph.getNeighbors(current)) {
        if (!visited.contains(neighbor.key)) {
          visited.add(neighbor.key);
          queue.add(neighbor.key);
        }
      }
    }
    return visitedOrder;
  }
  public static List<String> DFS(Graph graph, String startVertexKey) {
    List<String> visitedOrder = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    dfsHelper(graph, startVertexKey, visited, visitedOrder);
    return visitedOrder;
  }
  private static void dfsHelper(Graph graph, String current, Set<String> visited, List<String> visitedOrder) {
    visited.add(current);
    visitedOrder.add(current);
    for (Graph.Vertex neighbor : graph.getNeighbors(current)) {
      if (!visited.contains(neighbor.key)) {
        dfsHelper(graph, neighbor.key, visited, visitedOrder);
      }
    }
  }
  public static Map<String, Integer> dijkstra(Graph graph, String startVertexKey) {
    Map<String, Integer> distances = new HashMap<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      distances.put(v.key, Integer.MAX_VALUE);
    }
    distances.put(startVertexKey, 0);
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
    pq.add(new java.util.AbstractMap.SimpleEntry<>(startVertexKey, 0));
    Set<String> visited = new HashSet<>();
    while (!pq.isEmpty()) {
      Map.Entry<String, Integer> entry = pq.poll();
      String u = entry.getKey();
      if (visited.contains(u)) continue;
      visited.add(u);
      List<Graph.Edge> edges = graph.adjacencyList.get(u);
      if (edges != null) {
        for (Graph.Edge edge : edges) {
          String v = edge.end.key;
          int weight = edge.weight;
          if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + weight < distances.get(v)) {
            distances.put(v, distances.get(u) + weight);
            pq.add(new java.util.AbstractMap.SimpleEntry<>(v, distances.get(v)));
          }
        }
      }
    }
    return distances;
  }
  public static Map<String, Integer> bellmanFord(Graph graph, String startVertexKey) {
    Map<String, Integer> distances = new HashMap<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      distances.put(v.key, Integer.MAX_VALUE);
    }
    distances.put(startVertexKey, 0);
    int vertexCount = graph.getAllVertices().size();
    List<Graph.Edge> edges = graph.getAllEdges();
    for (int i = 0; i < vertexCount - 1; i++) {
      for (Graph.Edge edge : edges) {
        String u = edge.start.key;
        String v = edge.end.key;
        if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + edge.weight < distances.get(v)) {
          distances.put(v, distances.get(u) + edge.weight);
        }
      }
    }
    return distances;
  }
  public static int[][] floydWarshall(int[][] graph) {
    int v = graph.length;
    int[][] dist = new int[v][v];
    for (int i = 0; i < v; i++) {
      for (int j = 0; j < v; j++) {
        dist[i][j] = graph[i][j];
      }
    }
    for (int k = 0; k < v; k++) {
      for (int i = 0; i < v; i++) {
        for (int j = 0; j < v; j++) {
          if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    return dist;
  }
  public static List<Graph.Edge> kruskal(Graph graph) {
    List<Graph.Edge> mst = new ArrayList<>();
    List<Graph.Edge> allEdges = graph.getAllEdges();
    allEdges.sort(Comparator.comparingInt(e -> e.weight));
    DisjointSet<String> ds = new DisjointSet<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      ds.makeSet(v.key);
    }
    for (Graph.Edge edge : allEdges) {
      if (!ds.connected(edge.start.key, edge.end.key)) {
        ds.union(edge.start.key, edge.end.key);
        mst.add(edge);
      }
    }
    return mst;
  }
  public static List<Graph.Edge> prim(Graph graph, String startVertexKey) {
    List<Graph.Edge> mst = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    PriorityQueue<Graph.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    visited.add(startVertexKey);
    if (graph.adjacencyList.containsKey(startVertexKey)) {
      pq.addAll(graph.adjacencyList.get(startVertexKey));
    }
    while (!pq.isEmpty() && visited.size() < graph.getAllVertices().size()) {
      Graph.Edge edge = pq.poll();
      if (visited.contains(edge.end.key)) continue;
      visited.add(edge.end.key);
      mst.add(edge);
      for (Graph.Edge nextEdge : graph.adjacencyList.get(edge.end.key)) {
        if (!visited.contains(nextEdge.end.key)) {
          pq.add(nextEdge);
        }
      }
    }
    return mst;
  }
  public static boolean detectCycle(Graph graph) {
    if (graph.isDirected) {
      Set<String> visited = new HashSet<>();
      Set<String> recStack = new HashSet<>();
      for (Graph.Vertex v : graph.getAllVertices()) {
        if (detectCycleDirected(graph, v.key, visited, recStack)) {
          return true;
        }
      }
      return false;
    } else {
      Set<String> visited = new HashSet<>();
      for (Graph.Vertex v : graph.getAllVertices()) {
        if (!visited.contains(v.key)) {
          if (detectCycleUndirected(graph, v.key, visited, null)) {
            return true;
          }
        }
      }
      return false;
    }
  }
  private static boolean detectCycleDirected(Graph graph, String curr, Set<String> visited, Set<String> recStack) {
    if (recStack.contains(curr)) return true;
    if (visited.contains(curr)) return false;
    visited.add(curr);
    recStack.add(curr);
    for (Graph.Vertex neighbor : graph.getNeighbors(curr)) {
      if (detectCycleDirected(graph, neighbor.key, visited, recStack)) {
        return true;
      }
    }
    recStack.remove(curr);
    return false;
  }
  private static boolean detectCycleUndirected(Graph graph, String curr, Set<String> visited, String parent) {
    visited.add(curr);
    for (Graph.Vertex neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor.key)) {
        if (detectCycleUndirected(graph, neighbor.key, visited, curr)) {
          return true;
        }
      } else if (!neighbor.key.equals(parent)) {
        return true;
      }
    }
    return false;
  }
  public static List<String> topologicalSort(Graph graph) {
    List<String> order = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      if (!visited.contains(v.key)) {
        topologicalSortHelper(graph, v.key, visited, order);
      }
    }
    Collections.reverse(order);
    return order;
  }
  private static void topologicalSortHelper(Graph graph, String curr, Set<String> visited, List<String> order) {
    visited.add(curr);
    for (Graph.Vertex neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor.key)) {
        topologicalSortHelper(graph, neighbor.key, visited, order);
      }
    }
    order.add(curr);
  }
  public static List<String> tarjanArticulationPoints(Graph graph) {
    List<String> ap = new ArrayList<>();
    Map<String, Integer> tin = new HashMap<>();
    Map<String, Integer> low = new HashMap<>();
    Set<String> visited = new HashSet<>();
    int[] timer = new int[]{0};
    for (Graph.Vertex v : graph.getAllVertices()) {
      if (!visited.contains(v.key)) {
        dfsAp(graph, v.key, null, visited, tin, low, timer, ap);
      }
    }
    return ap;
  }
  private static void dfsAp(Graph graph, String u, String p, Set<String> visited, Map<String, Integer> tin, Map<String, Integer> low, int[] timer, List<String> ap) {
    visited.add(u);
    tin.put(u, timer[0]);
    low.put(u, timer[0]);
    timer[0]++;
    int children = 0;
    for (Graph.Vertex vNode : graph.getNeighbors(u)) {
      String v = vNode.key;
      if (v.equals(p)) continue;
      if (visited.contains(v)) {
        low.put(u, Math.min(low.get(u), tin.get(v)));
      } else {
        dfsAp(graph, v, u, visited, tin, low, timer, ap);
        low.put(u, Math.min(low.get(u), low.get(v)));
        if (low.get(v) >= tin.get(u) && p != null && !ap.contains(u)) {
          ap.add(u);
        }
        children++;
      }
    }
    if (p == null && children > 1 && !ap.contains(u)) {
      ap.add(u);
    }
  }
  public static List<String> bridges(Graph graph) {
    List<String> bridgesList = new ArrayList<>();
    Map<String, Integer> tin = new HashMap<>();
    Map<String, Integer> low = new HashMap<>();
    Set<String> visited = new HashSet<>();
    int[] timer = new int[]{0};
    for (Graph.Vertex v : graph.getAllVertices()) {
      if (!visited.contains(v.key)) {
        dfsBridge(graph, v.key, null, visited, tin, low, timer, bridgesList);
      }
    }
    return bridgesList;
  }
  private static void dfsBridge(Graph graph, String u, String p, Set<String> visited, Map<String, Integer> tin, Map<String, Integer> low, int[] timer, List<String> bridgesList) {
    visited.add(u);
    tin.put(u, timer[0]);
    low.put(u, timer[0]);
    timer[0]++;
    for (Graph.Vertex vNode : graph.getNeighbors(u)) {
      String v = vNode.key;
      if (v.equals(p)) continue;
      if (visited.contains(v)) {
        low.put(u, Math.min(low.get(u), tin.get(v)));
      } else {
        dfsBridge(graph, v, u, visited, tin, low, timer, bridgesList);
        low.put(u, Math.min(low.get(u), low.get(v)));
        if (low.get(v) > tin.get(u)) {
          bridgesList.add(u + "-" + v);
        }
      }
    }
  }
  public static List<List<String>> kosarajuSCC(Graph graph) {
    List<List<String>> sccList = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    List<String> finishOrder = new ArrayList<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      if (!visited.contains(v.key)) {
        dfsFinishOrder(graph, v.key, visited, finishOrder);
      }
    }
    Graph transposed = new Graph(true);
    for (Graph.Vertex v : graph.getAllVertices()) {
      transposed.addVertex(v.key);
    }
    for (Graph.Edge edge : graph.getAllEdges()) {
      transposed.addEdge(edge.end.key, edge.start.key, edge.weight);
    }
    visited.clear();
    Collections.reverse(finishOrder);
    for (String key : finishOrder) {
      if (!visited.contains(key)) {
        List<String> component = new ArrayList<>();
        dfsCollectScc(transposed, key, visited, component);
        sccList.add(component);
      }
    }
    return sccList;
  }
  private static void dfsFinishOrder(Graph graph, String curr, Set<String> visited, List<String> order) {
    visited.add(curr);
    for (Graph.Vertex neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor.key)) {
        dfsFinishOrder(graph, neighbor.key, visited, order);
      }
    }
    order.add(curr);
  }
  private static void dfsCollectScc(Graph graph, String curr, Set<String> visited, List<String> component) {
    visited.add(curr);
    component.add(curr);
    for (Graph.Vertex neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor.key)) {
        dfsCollectScc(graph, neighbor.key, visited, component);
      }
    }
  }
  public static int TSP(int[][] dist, int n) {
    int numStates = 1 << n;
    int[][] memo = new int[numStates][n];
    for (int[] row : memo) Arrays.fill(row, -1);
    return tspHelper(1, 0, n, dist, memo);
  }
  private static int tspHelper(int mask, int pos, int n, int[][] dist, int[][] memo) {
    if (mask == (1 << n) - 1) {
      return dist[pos][0];
    }
    if (memo[mask][pos] != -1) return memo[mask][pos];
    int ans = Integer.MAX_VALUE / 2;
    for (int next = 0; next < n; next++) {
      if ((mask & (1 << next)) == 0) {
        int newAns = dist[pos][next] + tspHelper(mask | (1 << next), next, n, dist, memo);
        ans = Math.min(ans, newAns);
      }
    }
    return memo[mask][pos] = ans;
  }
  public static List<List<String>> findHamiltonianCycles(int[][] adjMatrix, List<String> vertexNames) {
    List<List<String>> cycles = new ArrayList<>();
    int n = adjMatrix.length;
    if (n == 0) return cycles;
    List<Integer> path = new ArrayList<>();
    path.add(0);
    boolean[] inPath = new boolean[n];
    inPath[0] = true;
    hamiltonianDfs(adjMatrix, vertexNames, path, inPath, cycles, n);
    return cycles;
  }
  private static void hamiltonianDfs(int[][] adj, List<String> names, List<Integer> path, boolean[] inPath, List<List<String>> cycles, int n) {
    if (path.size() == n) {
      if (adj[path.get(path.size() - 1)][path.get(0)] > 0) {
        List<String> cycle = new ArrayList<>();
        for (int idx : path) cycle.add(names.get(idx));
        cycle.add(names.get(path.get(0)));
        cycles.add(cycle);
      }
      return;
    }
    int last = path.get(path.size() - 1);
    for (int next = 0; next < n; next++) {
      if (adj[last][next] > 0 && !inPath[next]) {
        inPath[next] = true;
        path.add(next);
        hamiltonianDfs(adj, names, path, inPath, cycles, n);
        path.remove(path.size() - 1);
        inPath[next] = false;
      }
    }
  }
  public static List<String> eulerianPath(Graph graph) {
    List<String> path = new ArrayList<>();
    Map<String, List<String>> adj = new HashMap<>();
    Map<String, Integer> inDegree = new HashMap<>();
    Map<String, Integer> outDegree = new HashMap<>();
    for (Graph.Vertex v : graph.getAllVertices()) {
      adj.put(v.key, new ArrayList<>());
      inDegree.put(v.key, 0);
      outDegree.put(v.key, 0);
    }
    for (Graph.Edge e : graph.getAllEdges()) {
      adj.get(e.start.key).add(e.end.key);
      outDegree.put(e.start.key, outDegree.get(e.start.key) + 1);
      inDegree.put(e.end.key, inDegree.get(e.end.key) + 1);
    }
    String startNode = graph.getAllVertices().isEmpty() ? null : graph.getAllVertices().get(0).key;
    for (Graph.Vertex v : graph.getAllVertices()) {
      if (outDegree.get(v.key) - inDegree.get(v.key) == 1) {
        startNode = v.key;
        break;
      }
    }
    if (startNode == null) return path;
    ArrayDeque<String> stack = new ArrayDeque<>();
    stack.push(startNode);
    while (!stack.isEmpty()) {
      String curr = stack.peek();
      List<String> neighbors = adj.get(curr);
      if (neighbors != null && !neighbors.isEmpty()) {
        String next = neighbors.remove(neighbors.size() - 1);
        stack.push(next);
      } else {
        path.add(stack.pop());
      }
    }
    Collections.reverse(path);
    return path;
  }
}