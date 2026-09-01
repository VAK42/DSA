import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
public class Graph {
  public static class Vertex {
    public String key;
    public Vertex(String key) {
      this.key = key;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Vertex vertex = (Vertex) o;
      return Objects.equals(key, vertex.key);
    }
    @Override
    public int hashCode() {
      return Objects.hash(key);
    }
    @Override
    public String toString() {
      return key;
    }
  }
  public static class Edge {
    public Vertex start;
    public Vertex end;
    public int weight;
    public Edge(Vertex start, Vertex end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
    public Edge(Vertex start, Vertex end) {
      this(start, end, 0);
    }
    public String getKey() {
      return start.key + "_" + end.key;
    }
  }
  public boolean isDirected;
  public Map<String, Vertex> vertices;
  public Map<String, Edge> edges;
  public Map<String, List<Edge>> adjacencyList;
  public Graph(boolean isDirected) {
    this.isDirected = isDirected;
    this.vertices = new HashMap<>();
    this.edges = new HashMap<>();
    this.adjacencyList = new HashMap<>();
  }
  public Graph() {
    this(false);
  }
  public Vertex addVertex(String key) {
    if (!vertices.containsKey(key)) {
      Vertex v = new Vertex(key);
      vertices.put(key, v);
      adjacencyList.put(key, new ArrayList<>());
      return v;
    }
    return vertices.get(key);
  }
  public Edge addEdge(String startKey, String endKey, int weight) {
    Vertex start = addVertex(startKey);
    Vertex end = addVertex(endKey);
    Edge edge = new Edge(start, end, weight);
    edges.put(edge.getKey(), edge);
    adjacencyList.get(startKey).add(edge);
    if (!isDirected) {
      Edge reverseEdge = new Edge(end, start, weight);
      edges.put(reverseEdge.getKey(), reverseEdge);
      adjacencyList.get(endKey).add(reverseEdge);
    }
    return edge;
  }
  public Edge addEdge(String startKey, String endKey) {
    return addEdge(startKey, endKey, 0);
  }
  public Vertex getVertexByKey(String key) {
    return vertices.get(key);
  }
  public List<Vertex> getNeighbors(String key) {
    List<Vertex> neighbors = new ArrayList<>();
    List<Edge> outEdges = adjacencyList.get(key);
    if (outEdges != null) {
      for (Edge edge : outEdges) {
        neighbors.add(edge.end);
      }
    }
    return neighbors;
  }
  public List<Vertex> getAllVertices() {
    return new ArrayList<>(vertices.values());
  }
  public List<Edge> getAllEdges() {
    return new ArrayList<>(edges.values());
  }
  public int getWeight() {
    int weight = 0;
    for (Edge edge : edges.values()) {
      weight += edge.weight;
    }
    return isDirected ? weight : weight / 2;
  }
}