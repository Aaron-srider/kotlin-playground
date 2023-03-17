package fit.wenchao.kotlinplayground.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Sorter {

    public enum SortType {
        DESC,
        ASC
    }

    public static <T> void sortList(Function<T, Object> sortField, List<T> list, SortType sortType) {
        Collections.sort(list, (item1, item2) -> {
            Object value1 = sortField.apply(item1);
            Object value2 = sortField.apply(item2);

            if(value1 instanceof Comparable && value2 instanceof Comparable) {
                if (sortType == SortType.DESC) {
                    return ((Comparable) value2).compareTo(value1);
                }

                if (sortType == SortType.ASC) {
                    return ((Comparable) value1).compareTo(value2);
                }
            }

            throw new RuntimeException("item must implements Comparable");
            //
            // if (value1 instanceof Integer
            // ) {
            //     if (sortType == SortType.DESC) {
            //         return (Integer) value2 - (Integer) value1;
            //     }
            // }
            //
            // if (value1 instanceof Long) {
            //     if (sortType == SortType.DESC) {
            //         return (Long) value2 - (Long) value1;
            //     }
            // }
            //
            // if (value1 instanceof Character) {
            //     if (sortType == SortType.DESC) {
            //         return (Character) value2 - (Character) value1;
            //     }
            // }
            //
            // if (value1 instanceof String) {
            //     if (sortType == SortType.DESC) {
            //         return ((String) value2).compareTo((String) value1);
            //     }
            // }

        });
    }

}
