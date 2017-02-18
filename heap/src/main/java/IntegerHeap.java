/**
 * Created by adarsh on 14/02/2017.
 */
public class IntegerHeap extends Heap<Integer> {
    public IntegerHeap(Integer... elements) {
        super(elements);
    }

    @Override
    protected void doAdd(int index, Integer toAdd) {
        elements[index] = (int) elements[index] + toAdd;
    }

    @Override
    protected void doSubtract(int index, Integer toSubtract) {
        elements[index] = (int) elements[index] - toSubtract;
    }
}
