import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh on 15/02/2017.
 */
public class GraphTest {

    @Test
    public void testTraversal() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        Assert.assertEquals("2031", graph.traverseBreadthFirst(2));
        Assert.assertEquals("2013", graph.traverseDepthFirst(2));

    }

    @Test
    public void testCyclic() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        Assert.assertTrue(graph.isCyclic());
    }

    @Test
    public void testCyclicUndirected() {
        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        Assert.assertFalse(graph.isCyclic());

        graph.addEdge(0, 2);
        Assert.assertTrue(graph.isCyclic());
    }

    @Test
    public void testCyclicUndirected2() {
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        Assert.assertTrue(graph.isCyclic2());

        graph = new UndirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        Assert.assertFalse(graph.isCyclic2());
    }

    @Test
    public void testTopologicalSort() {
        Node<Character> nodeA = new Node<>('A');
        Node<Character> nodeB = new Node<>('B');
        Node<Character> nodeC = new Node<>('C');
        Node<Character> nodeD = new Node<>('D');
        Node<Character> nodeE = new Node<>('E');
        Node<Character> nodeF = new Node<>('F');
        nodeA.addDependant(nodeD);
        nodeF.addDependant(nodeB);
        nodeB.addDependant(nodeD);
        nodeF.addDependant(nodeA);
        nodeD.addDependant(nodeC);
        String topologicalRep = Node.getTopologicalRepresentation(new ArrayList<>(Arrays.asList(
                nodeA, nodeB, nodeC, nodeD, nodeE, nodeF
        )));
    List<String> acceptableSolutions=Arrays.asList("EFABDC","EFBADC","FEABDC","FEBADC");
        Assert.assertTrue(acceptableSolutions.contains(topologicalRep));

    }

}
