package common;

public class EdgeInfo<V, E> {
    private V from;
    private V to;
    private E weight;
    public EdgeInfo(V from, V to, E weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    public V getFrom() {
        return from;
    }
    public void setFrom(V from) {
        this.from = from;
    }
    public V getTo() {
        return to;
    }
    public void setTo(V to) {
        this.to = to;
    }
    public E getWeight() {
        return weight;
    }
    public void setWeight(E weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "EdgeInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
    }

}
