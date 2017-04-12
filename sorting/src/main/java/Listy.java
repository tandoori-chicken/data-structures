import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh on 12/04/2017.
 */
public class Listy{

    private List<Integer> contents;

    public int elementAt(int index)
    {
        if(index<this.contents.size())
            return contents.get(index);
        return -1;
    }

    public Listy(Integer... contents) {
        this.contents = Arrays.asList(contents);
    }
}
