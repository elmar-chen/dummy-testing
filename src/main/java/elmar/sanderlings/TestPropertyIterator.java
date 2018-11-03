package elmar.sanderlings;

import java.util.Iterator;
import java.util.List;

public class TestPropertyIterator implements Iterator<TestProperty>{
	List<TestProperty> properties;

	@Override
	public boolean hasNext() {
		throw new RuntimeException();
	}

	@Override
	public TestProperty next() {
		throw new RuntimeException();
	}

	public void addTestProperty(TestProperty property) {
		properties.add(property);
	}
	
}
