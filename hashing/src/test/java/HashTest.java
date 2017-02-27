import org.junit.Assert;
import org.junit.Test;

import java.util.*;


/**
 * Created by adarsh on 15/02/2017.
 */
public class HashTest {
    @Test
    public void testPrintVerticalOrderTree() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);
        root.right.left.right = new Node<>(8);
        root.right.right.right = new Node<>(9);

        java.util.Map<Integer, List<Integer>> map = new HashMap<>();
        MinMaxDTO dto = new MinMaxDTO(0, 0);
        parseTree(root, dto, map, 0);
        for (int i = dto.min; i <= dto.max; i++) {
            System.out.println(map.get(i).stream().map(Object::toString).reduce((a, b) -> a + " " + b).get());
        }

    }

    private <T> void parseTree(Node<T> node, MinMaxDTO dto, java.util.Map<Integer, List<T>> lookupMap, int curDistance) {
        if (node == null)
            return;
        if (curDistance < dto.min)
            dto.min = curDistance;
        if (curDistance > dto.max)
            dto.max = curDistance;

        if (lookupMap.containsKey(curDistance)) {
            lookupMap.get(curDistance).add(node.data);
        } else {
            lookupMap.put(curDistance, new ArrayList<>(Collections.singleton(node.data)));
        }

        parseTree(node.left, dto, lookupMap, curDistance - 1);
        parseTree(node.right, dto, lookupMap, curDistance + 1);

    }

    @Test
    public void testEmployeesUnderEmployee() {
        java.util.Map<String, String> employeeMap = new HashMap<>(6);
        employeeMap.put("A", "C");
        employeeMap.put("B", "C");
        employeeMap.put("C", "F");
        employeeMap.put("D", "E");
        employeeMap.put("E", "F");
        employeeMap.put("F", "F");

        java.util.Map<String, Integer> underEmployeesCountMap = getUnderEmployeesCount(employeeMap);

        assertMapContent(0, "A", underEmployeesCountMap);
        assertMapContent(0, "B", underEmployeesCountMap);
        assertMapContent(2, "C", underEmployeesCountMap);
        assertMapContent(0, "D", underEmployeesCountMap);
        assertMapContent(1, "E", underEmployeesCountMap);
        assertMapContent(5, "F", underEmployeesCountMap);
    }

    private <K, V> void assertMapContent(V expected, K key, java.util.Map<K, V> map) {
        Assert.assertEquals(expected, map.get(key));
    }

    java.util.Map<String, Integer> getUnderEmployeesCount(java.util.Map<String, String> employeeMap) {
        java.util.Map<String, Set<String>> reverseMap = new HashMap<>(employeeMap.size());
        for (String key : employeeMap.keySet()) {
            reverseMap.put(key, new HashSet<>());
        }
        for (String key : employeeMap.keySet()) {
            String value = employeeMap.get(key);
            reverseMap.get(value).add(key);
        }

        java.util.Map<String, Integer> underEmployeeCountMap = new HashMap<>(employeeMap.size());
        for (String key : reverseMap.keySet()) {
            underEmployeeCountMap.put(key, getCount(key, reverseMap));
        }


        return underEmployeeCountMap;
    }

    private int getCount(String key, java.util.Map<String, Set<String>> reverseMap) {
        int count = 0;
        for (String value : reverseMap.get(key)) {
                if (!value.equals(key))
                    count += getCount(value, reverseMap) + 1; //count underemployees plus count manager
        }
        return count;
    }
}

