package elmar.sanderlings;

import java.util.Collections;
import java.util.List;

public class Sort {
	public static <T extends Comparable<T>> void sort(List<T> input) {
		Collections.sort(input);
	};
}
