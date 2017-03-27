import animals.Animal;

import java.util.LinkedList;

/**
 * Created by adarsh on 27/03/2017.
 */
public class QueueOfAnimals {

    LinkedList<Animal.Cat> cats = new LinkedList<>();
    LinkedList<Animal.Dog> dogs = new LinkedList<>();
    int order = 0;

    public Animal deQueueAny() {
        if (cats.isEmpty() && dogs.isEmpty())
            throw new UnsupportedOperationException("No animals in shelter");
        if (cats.isEmpty())
            return dogs.poll();
        if (dogs.isEmpty())
            return cats.poll();
        return (dogs.peek().compareTo(cats.peek()) < 0) ? dogs.poll() : cats.poll();
    }

    public Animal.Dog dequeueDog() {
        if (dogs.isEmpty())
            throw new UnsupportedOperationException("No dogs in shelter");
        return dogs.poll();

    }

    public Animal.Cat dequeueCat() {
        if (cats.isEmpty())
            throw new UnsupportedOperationException("No cats in shelter");
        return cats.poll();
    }

    public void enqueue(Animal animal) {
        animal.order = order++;
        if (animal instanceof Animal.Cat) {
            cats.offer((Animal.Cat) animal);
        } else {
            dogs.offer((Animal.Dog) animal);
        }
    }

    public boolean isEmpty()
    {
        return dogs.isEmpty()&&cats.isEmpty();
    }


}
