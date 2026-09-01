import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class MLStatistics {
  public static class DataPoint {
    public double[] features;
    public String label;
    public DataPoint(double[] features, String label) {
      this.features = features;
      this.label = label;
    }
  }
  public static String KNN(List<DataPoint> trainingSet, double[] targetFeatures, int k) {
    List<java.util.Map.Entry<DataPoint, Double>> distances = new ArrayList<>();
    for (DataPoint point : trainingSet) {
      double dist = MathAlgorithms.euclideanDistance(point.features, targetFeatures);
      distances.add(new java.util.AbstractMap.SimpleEntry<>(point, dist));
    }
    distances.sort(Comparator.comparingDouble(java.util.Map.Entry::getValue));
    Map<String, Integer> votes = new HashMap<>();
    for (int i = 0; i < Math.min(k, distances.size()); i++) {
      String label = distances.get(i).getKey().label;
      votes.put(label, votes.getOrDefault(label, 0) + 1);
    }
    String bestLabel = null;
    int maxVotes = -1;
    for (Map.Entry<String, Integer> entry : votes.entrySet()) {
      if (entry.getValue() > maxVotes) {
        maxVotes = entry.getValue();
        bestLabel = entry.getKey();
      }
    }
    return bestLabel;
  }
  public static List<List<double[]>> KMeans(List<double[]> data, int k, int maxIterations) {
    List<double[]> centroids = new ArrayList<>();
    Random random = new Random();
    int dimensions = data.get(0).length;
    for (int i = 0; i < k; i++) {
      centroids.add(data.get(random.nextInt(data.size())).clone());
    }
    List<List<double[]>> clusters = new ArrayList<>();
    for (int iter = 0; iter < maxIterations; iter++) {
      clusters.clear();
      for (int i = 0; i < k; i++) clusters.add(new ArrayList<>());
      for (double[] point : data) {
        int bestCentroid = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < k; i++) {
          double dist = MathAlgorithms.euclideanDistance(point, centroids.get(i));
          if (dist < minDistance) {
            minDistance = dist;
            bestCentroid = i;
          }
        }
        clusters.get(bestCentroid).add(point);
      }
      for (int i = 0; i < k; i++) {
        List<double[]> cluster = clusters.get(i);
        if (cluster.isEmpty()) continue;
        double[] newCentroid = new double[dimensions];
        for (double[] point : cluster) {
          for (int d = 0; d < dimensions; d++) {
            newCentroid[d] += point[d];
          }
        }
        for (int d = 0; d < dimensions; d++) {
          newCentroid[d] /= cluster.size();
        }
        centroids.set(i, newCentroid);
      }
    }
    return clusters;
  }
  public static <T> T weightedRandom(List<T> items, List<Double> weights) {
    if (items.size() != weights.size() || items.isEmpty()) {
      throw new IllegalArgumentException();
    }
    List<Double> cumulativeWeights = new ArrayList<>();
    double totalWeight = 0;
    for (double weight : weights) {
      totalWeight += weight;
      cumulativeWeights.add(totalWeight);
    }
    double randomVal = new Random().nextDouble() * totalWeight;
    for (int i = 0; i < cumulativeWeights.size(); i++) {
      if (randomVal <= cumulativeWeights.get(i)) {
        return items.get(i);
      }
    }
    return items.get(items.size() - 1);
  }
}