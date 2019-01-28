package elmar.sanderlings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompositeIterator<T> implements Iterator<List<T>> {

	private ArrayList<Iterable<T>> components;

	private ArrayList<Iterator<T>> iterators;

	private List<T> lastResult = new ArrayList<>();

	private boolean eof = false;
	private boolean prefetched = false;

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
				if (!iter.hasNext()) {
					iter = components.get(i).iterator();
					carry = true;
				} else {
					carry = false;
				}
				if (iter.hasNext()) {
					rslt.set(i, iter.next());
				} else {
					carry = true;
					break;
				}
			} else {
				rslt.set(i, lastResult.get(i));
			}
		}
		return carry ? null : rslt;
	}

	@Override
	public List<T> next() {
		advance();
		return 
	}

	public static void main(String[] args) {
		List<Iterable<Integer>> ll = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3),
				Arrays.asList(1, 2, 3));
		CompositeIterator<Integer> iter = new CompositeIterator<Integer>(ll);
		List<Integer> next;
		while ((next = iter.next()) != null) {
			System.out.println(next);
		}
	}
}
