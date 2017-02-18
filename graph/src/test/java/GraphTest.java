import org.junit.Assert;
import org.junit.Test;

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
    public void testCyclicUndirected2(){
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

}
