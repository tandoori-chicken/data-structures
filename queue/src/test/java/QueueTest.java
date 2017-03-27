import animals.Animal;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by adarsh on 27/03/2017.
 */
public class QueueTest {

    @Test
    public void testKthEntry() {
        /**
         * Aim is to get the Kth entry in a sorted series of numbers whose prime factors are 3,5 and 7.
         * 1,3,5,7,9,15,21,27,...
         */
        Assert.assertEquals(3, getKthEntry(2));
        Assert.assertEquals(21, getKthEntry(7));
        System.out.println(getKthEntry(100));


    }

    private int getKthEntry(int k) {
        if (k == 1)
            return 1;

        Queue<Integer> queue = new LinkedList<>(Collections.singleton(1));
        int i = 1;
        int addedCount = 1;
        TreeSet<Integer> toAdd = new TreeSet<>();

        while (!queue.isEmpty()) {
            while (addedCount-- > 0) {
                int removedEntry = queue.remove();
                toAdd.add(removedEntry * 3);
                toAdd.add(removedEntry * 5);
                toAdd.add(removedEntry * 7);
            }
            addedCount = toAdd.size();
            while (!toAdd.isEmpty()) {
                int removed = toAdd.pollFirst();
                queue.add(removed);
                i++;
                if (i == k)
                    return removed;
            }
        }
        throw new IllegalArgumentException("Should not reach here");
    }

    @Test
    public void testAnimalQueue()
    {
        QueueOfAnimals queue = new QueueOfAnimals();
        queue.enqueue(new Animal.Cat());
        queue.enqueue(new Animal.Dog());

        Assert.assertTrue(queue.deQueueAny() instanceof Animal.Cat);
        Assert.assertTrue(queue.deQueueAny() instanceof Animal.Dog);

        queue.enqueue(new Animal.Cat());
        queue.enqueue(new Animal.Cat());
        queue.enqueue(new Animal.Dog());
        Assert.assertNotNull(queue.dequeueDog());

    }

}
