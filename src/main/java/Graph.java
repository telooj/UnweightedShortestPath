import java.util.*;

public class Graph {
    public LinkedList<Vertex> Vertices;

    Graph(int numberOfVertices) {
        Vertices = new LinkedList<Vertex>();
        for (int i = 0; i < numberOfVertices; i++) {
            Vertices.add(new Vertex(i));
        }
    }

    public void addEdge(Vertex source, Vertex destination) {
        source.Destinations.add(destination);
    }

    private void printGraph() {
        for (int i = 0; i < Vertices.size(); i++) {
            if(Vertices.get(i).Destinations.size()>0) {
                System.out.print("Vertex " + Vertices.get(i) + " is connected to: ");
                for (int j = 0; j < Vertices.get(i).Destinations.size(); j++) {
                    System.out.print(Vertices.get(i).Destinations.get(j) + " ");
                }
            }
            System.out.println();
        }
    }

    public static List<Vertex> findShortestPath(Vertex source, Vertex destination) {
        // Return null if either node is null or if they're the same node
        if (source == null || destination == null) return null;
        if (source == destination) return null;

        // Using a queue for our BFS
        Queue<Vertex> toVisit = new LinkedList<Vertex>();

        // Track the parents so that we can reconstruct our path
        HashMap<Vertex, Vertex> parents = new HashMap<Vertex, Vertex>();

        // Initialize the BFS
        toVisit.add(source);
        parents.put(source, null);

        // Keep going until we run out of nodes or reach our destination
        while (!toVisit.isEmpty()) {
            Vertex curr = toVisit.remove();

            // If we find the node we're looking for then we're done
            if (curr == destination) break;

            // If the current node doesn't have children, skip it
            if (curr.Destinations == null) continue;

            // Add all the children to the queue
            for (Vertex n : curr.Destinations) {
                if (!parents.containsKey(n)) {
                    toVisit.add(n);
                    parents.put(n, curr);
                }
            }
        }

        // If we couldn't find a path, the destination node won't have been
        // added to our parents set
        if (parents.get(destination) == null) return null;

        // Create the output list and add the path to the list
        List<Vertex> out = new LinkedList<Vertex>();
        Vertex n = destination;
        while (n != null) {
            out.add(0, n);
            n = parents.get(n);
        }

        return out;
    }

    public void printShortestPath(Vertex source, Vertex destination) {
        System.out.println(Graph.findShortestPath(source, destination));
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(g.Vertices.get(0),g.Vertices.get(1));
        g.addEdge(g.Vertices.get(0),g.Vertices.get(2));
        g.addEdge(g.Vertices.get(1),g.Vertices.get(2));
        g.addEdge(g.Vertices.get(2),g.Vertices.get(3));
        g.printGraph();
        for (int i = 0; i <4 ; i++) {
            System.out.println(g.Vertices.get(i) +": "+ g.Vertices.get(i).Destinations);
        }
        g.printShortestPath(g.Vertices.get(0), g.Vertices.get(3));
    }
}