package util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    public static double[] subarrayDouble(double[] array, int startIndex, int endIndex) {
        List<Double> result = new ArrayList<>();
        for (int i = startIndex; i < endIndex && i < array.length; i++)
            result.add(array[i]);
        return result.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
