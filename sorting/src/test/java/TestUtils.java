import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

/**
 * Created by adarsh on 11/04/2017.
 */
public abstract class TestUtils {
    private TestUtils() {
    }

    public static <T> String stringify(T[] array) {
        return StringUtils.join(array, ',');
    }

    public static String stringify(int[] array) {
        return StringUtils.join(array, ',');
    }

    public static void assertSorted(int... array) {
        Assert.assertNotNull("Array is null", array);
        Assert.assertNotEquals("Array is empty", 0, array.length);
        int prev = array[0];
        for (int i = 1; i < array.length; i++) {
            int curr = array[i];
            Assert.assertTrue(prev < curr);
            prev = curr;
        }

    }

}
