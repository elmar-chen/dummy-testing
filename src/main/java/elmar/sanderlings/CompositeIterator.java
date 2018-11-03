package elmar.sanderlings;

import java.util.Iterator;
import java.util.List;

public abstract class CompositeIterator implements Iterator<TestProperty>{
	
	private List<CompositeIterator> components;
	
	

	public CompositeIterator(List<CompositeIterator> components) {
		this.components = components;
	}

	@Override
	public boolean hasNext() {
		for (CompositeIterator compositeIterator : components) {
			if(compositeIterator.hasNext())return true;
		}
		return false;
	}

	@Override
	public TestProperty next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract void reset();
}
