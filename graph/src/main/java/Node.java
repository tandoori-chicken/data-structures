import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adarsh on 28/03/2017.
 */
public class Node<T> {
    T data;
    int incomingCount; //keeps track of how many nodes this node depends on
    List<Node<T>> dependants = new ArrayList<>(); //keeps track of nodes that depend on this node

    public Node(T data) {
        this.data = data;
        this.incomingCount=0;
    }

    public void addDependant(Node<T> neighbour) {
        this.dependants.add(neighbour);
        neighbour.incomingCount++;
    }

    public static <T> String getTopologicalRepresentation(List<Node<T>> graph) {
        StringBuilder sb = new StringBuilder();
        while (!graph.isEmpty()) {
            List<Node<T>> noIncomingNodes = graph.stream().filter(n -> n.incomingCount==0).collect(Collectors.toList());
            for (Node<T> node : noIncomingNodes) {
                sb.append(node.data);
                removeFromGraph(graph, node);
            }

        }
        return sb.toString();
    }

    private static <T> void removeFromGraph(List<Node<T>> graph, Node<T> node) {
        for (Node<T> dependant : node.dependants) {
            dependant.incomingCount--;
        }
        graph.remove(node);
    }


}
