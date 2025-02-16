package common;

public class Edge {
    private String node;
    private double weight;

    public Edge(String node, double weight) {
        this.node = node;
        this.weight = weight;
    }

    public String getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }
}
