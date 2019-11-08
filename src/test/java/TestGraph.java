import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class TestGraph {
    public Graph testGraph;


    public void createGraph () {
        testGraph = new Graph(8);
        testGraph.addEdge(testGraph.Vertices.get(0), testGraph.Vertices.get(3));
        testGraph.addEdge(testGraph.Vertices.get(1), testGraph.Vertices.get(3));
        testGraph.addEdge(testGraph.Vertices.get(2), testGraph.Vertices.get(4));
        testGraph.addEdge(testGraph.Vertices.get(3), testGraph.Vertices.get(5));
        testGraph.addEdge(testGraph.Vertices.get(4), testGraph.Vertices.get(1));
        testGraph.addEdge(testGraph.Vertices.get(4), testGraph.Vertices.get(7));
        testGraph.addEdge(testGraph.Vertices.get(5), testGraph.Vertices.get(0));
        testGraph.addEdge(testGraph.Vertices.get(6), testGraph.Vertices.get(4));
        testGraph.addEdge(testGraph.Vertices.get(7), testGraph.Vertices.get(2));


    }

    @Test
    public void testMethodShortestPathReturnsCorrectPath() {
        this.createGraph();
        List<Vertex> expectedResultA = new LinkedList<>();
        expectedResultA.add(0, testGraph.Vertices.get(1));
        expectedResultA.add(1, testGraph.Vertices.get(3));
        expectedResultA.add(2, testGraph.Vertices.get(5));
        expectedResultA.add(3, testGraph.Vertices.get(0));

        assertEquals(expectedResultA, testGraph.findShortestPath(testGraph.Vertices.get(1), testGraph.Vertices.get(0)));

        List<Vertex> expectedResultB = new LinkedList<>();
        expectedResultB.add(0, testGraph.Vertices.get(2));
        expectedResultB.add(1, testGraph.Vertices.get(4));
        expectedResultB.add(2, testGraph.Vertices.get(1));
        expectedResultB.add(3, testGraph.Vertices.get(3));

        assertEquals(expectedResultB, testGraph.findShortestPath(testGraph.Vertices.get(2), testGraph.Vertices.get(3)));
        //adding Edge changes the shortest path
        testGraph.addEdge(testGraph.Vertices.get(4), testGraph.Vertices.get(3));
        List<Vertex> expectedResultC = new LinkedList<>();
        expectedResultC.add(0, testGraph.Vertices.get(2));
        expectedResultC.add(1, testGraph.Vertices.get(4));
        expectedResultC.add(2, testGraph.Vertices.get(3));

        assertEquals(expectedResultC, testGraph.findShortestPath(testGraph.Vertices.get(2), testGraph.Vertices.get(3)));

    }

    @Test
    public void testMethodShortestPathReturnsNullOnNonExistingPath() {
        this.createGraph();
        assertNull(testGraph.findShortestPath(testGraph.Vertices.get(1), testGraph.Vertices.get(6)));
        assertNull(testGraph.findShortestPath(testGraph.Vertices.get(0), testGraph.Vertices.get(7)));
    }

    @Test
    public void testMethodShortestPathReturnsNullOnSourceEqualsDestination() {
        this.createGraph();
        assertNull(testGraph.findShortestPath(testGraph.Vertices.get(2), testGraph.Vertices.get(2)));
    }
}
