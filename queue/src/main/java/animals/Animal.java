package animals;

/**
 * Created by adarsh on 27/03/2017.
 */
public abstract class Animal implements Comparable<Animal>{
    public int order;
    public static class Cat extends Animal{}
    public static class Dog extends Animal{}

    @Override
    public int compareTo(Animal o) {
        return this.order-o.order;
    }
}


