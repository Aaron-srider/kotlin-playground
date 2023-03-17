package fit.wenchao.kotlinplayground.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {

    /**
     * Index the list into a map according to a specific property of the objects in
     * the list.
     *
     * @param <K>        property type to index against
     * @param <T>        corresponding object
     * @param list       object list
     * @param fieldName  the name of the property to be indexed
     * @param fieldClass the class of the propery to be indexed
     * @return
     */
    public static <K, T> Map<K, T> indexListBy(List<T> list, String fieldName, Class<K> fieldClass) {
        Map<K, T> map = new HashMap<>();
        for (T elem : list) {
            K value = null;
            try {
                value = ClassUtils.getFieldValue(elem, fieldName, fieldClass);
            }
            catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            map.put(value, elem);
        }
        return map;
    }
}
