import java.util.*;

/**
 * Created by adarsh on 15/02/2017.
 */
public class Graph {
    private int vertexCount;
    private List<List<Integer>> adjacencyList;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacencyList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++)
            adjacencyList.add(new ArrayList<>());
    }

    public void addEdge(int srcIndex, int destIndex) {
        this.adjacencyList.get(srcIndex).add(destIndex);
    }


    public String traverseBreadthFirst(int startIndex) {
        boolean[] visited = new boolean[vertexCount];
        StringBuilder bfsBuilder = new StringBuilder();
        visited[startIndex] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int curIndex = queue.remove();
            bfsBuilder.append(curIndex);
            for (Integer adjacentIndex : adjacencyList.get(curIndex)) {
                if (!visited[adjacentIndex]) {
                    visited[adjacentIndex] = true;
                    queue.add(adjacentIndex);
                }
            }
        }

        return bfsBuilder.toString();
    }

    public String traverseDepthFirst(int startIndex) {
        return traverseDepthFirst(startIndex, new boolean[vertexCount]);
    }

    private String traverseDepthFirst(int curIndex, boolean[] visited) {
        visited[curIndex] = true;
        StringBuilder dfsBuilder = new StringBuilder();
        dfsBuilder.append(curIndex);

        for (int adjacentIndex : adjacencyList.get(curIndex)) {
            if (!visited[adjacentIndex])
                dfsBuilder.append(traverseDepthFirst(adjacentIndex, visited));
        }

        return dfsBuilder.toString();
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[this.vertexCount];
        boolean[] recursionStack = new boolean[this.vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            if (isCyclicUtil(i, visited, recursionStack))
                return true;
        }
        return false;

    }

    private boolean isCyclicUtil(int index, boolean[] visited, boolean[] recursionStack) {
        if (!visited[index]) {
            visited[index] = true;
            recursionStack[index] = true;

            for (int adjacentIndex : this.adjacencyList.get(index)) {
                if (!visited[adjacentIndex] && isCyclicUtil(adjacentIndex, visited, recursionStack))
                    return true;
                if (recursionStack[adjacentIndex])
                    return true;
            }
        }
        recursionStack[index] = false;
        return false;
    }
}
