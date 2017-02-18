import java.util.ArrayList;
import java.util.List;

/**
 * Another implementation of graph to understand union find algorithm to detect cycles
 */
public class UndirectedGraph {
    public int vertexCount;
    public List<Edge> edges;
    public List<List<Integer>> adjacencyList;

    public static class Edge {
        public int fromIndex, toIndex;

        public Edge(int fromIndex, int toIndex) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
    }

    public UndirectedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.edges = new ArrayList<>(vertexCount);
        this.adjacencyList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++)
            this.adjacencyList.add(new ArrayList<>());
    }

    public void addEdge(int srcIndex, int destIndex) {
        this.edges.add(new Edge(srcIndex, destIndex));
        this.adjacencyList.get(srcIndex).add(destIndex);
        this.adjacencyList.get(destIndex).add(srcIndex);
    }

    public int findParentIndex(int[] parentIndices, int index) {
        if (parentIndices[index] == -1)
            return index;
        return findParentIndex(parentIndices, parentIndices[index]);
    }

    public void union(int[] parentIndices, int index1, int index2) {
        int parentIndex1 = this.findParentIndex(parentIndices, index1);
        int parentIndex2 = this.findParentIndex(parentIndices, index2);
        parentIndices[parentIndex1] = parentIndex2;
    }

    public boolean isCyclic() {
        int[] parentIndices = new int[this.vertexCount];
        for (int i = 0; i < this.vertexCount; i++) {
            parentIndices[i] = -1;
        }

        for (Edge edge : edges) {
            int fromParentIndex = this.findParentIndex(parentIndices, edge.fromIndex);
            int toParentIndex = this.findParentIndex(parentIndices, edge.toIndex);
            if (fromParentIndex == toParentIndex)
                return true;
            else
                this.union(parentIndices, fromParentIndex, toParentIndex);
        }
        return false;
    }

    public boolean isCyclicUtil(int index, boolean[] visited, int parentIndex) {
        visited[index] = true;
        for (int adjacentIndex : this.adjacencyList.get(index)) {
            if (!visited[adjacentIndex]) {
                if (isCyclicUtil(adjacentIndex, visited, index))
                    return true;
            } else if (adjacentIndex != parentIndex)
                return true;
        }
        return false;
    }

    public boolean isCyclic2() {
        boolean[] visited = new boolean[this.vertexCount];
        for (int i = 0; i < this.vertexCount; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, -1))
                    return true;
            }
        }
        return false;
    }
}
