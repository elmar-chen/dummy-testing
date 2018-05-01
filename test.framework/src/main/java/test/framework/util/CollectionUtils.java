package test.framework.util;

import java.util.Arrays;

public class CollectionUtils{
	
	public static <T> int indexOf(Iterable<T> iterable, T obj) {
 		int i = -1;
 		for (T t : iterable) {
 			i++;
			if((obj==null && t==null) || t.equals(obj)) {
				return i;
			}
		}
 		return -1;
 	};
 	public static <T> int indexOf(T[] arr, T obj) {
 		return indexOf(Arrays.asList(arr), obj);
 	};
}
