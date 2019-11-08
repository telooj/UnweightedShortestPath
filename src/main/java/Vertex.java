import java.util.LinkedList;

public class Vertex {
    public int number;
    public LinkedList<Vertex> Destinations;
//    public int dist;
    public Vertex prev;

    public Vertex(int number) {
        Destinations = new LinkedList<Vertex>();
        this.number = number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
