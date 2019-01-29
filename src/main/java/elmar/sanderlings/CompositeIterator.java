package elmar.sanderlings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompositeIterator<T> implements Iterator<List<T>> {

	private ArrayList<Iterable<T>> components;

	private ArrayList<Iterator<T>> iterators;

	private List<T> lastResult = new ArrayList<>();

	private boolean eof = false;  boolean prefetched = false;

	public CompositeIterator(List<Iterable<T>> components) {
		this.components = new ArrayList<>(components);
		this.iterators = new ArrayList<>(components.size());
		for (Iterable<T> iterable : this.components) {
			Iterator<T> iter = iterable.iterator();
			iterators.add(iter);
			if (iter.hasNext()) {
				lastResult.add(iter.next());
			} else {
				eof = true;
				break;
			}
		}
		prefetched = true;
	}

	@Override
	public boolean hasNext() {
		advance();
		return prefetched && !eof;
	}

	private void advance() {
		if (prefetched || eof)
			return;
		List<T> rslt = new ArrayList<T>(lastResult);
		boolean carry = true;
		for (int i = iterators.size() - 1; i >= 0; i--) {
			if (carry) {
				Iterator<T> iter = iterators.get(i);
				if (iter.hasNext()) {
					rslt.set(i, iter.next());
					carry = false;
				} else {
					iter = components.get(i).iterator();
					iterators.set(i, iter);
					rslt.set(i, iter.next());
					carry = true;
				}
			} else {
				rslt.set(i, lastResult.get(i));
			}
		}
		if (carry) {
			eof = true;
		}
		lastResult = rslt;
		prefetched = true;
	}

	@Override
	public List<T> next() {
		advance();
		prefetched = false;
		return lastResult;
	}

	public static void main(String[] args) {
		List<Iterable<Integer>> ll = Arrays.asList(Arrays.asList(1,2), Arrays.asList(1),
				Arrays.asList(1, 2, 3),Arrays.asList(1, 2, 3));
		CompositeIterator<Integer> iter = new CompositeIterator<Integer>(ll);
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
