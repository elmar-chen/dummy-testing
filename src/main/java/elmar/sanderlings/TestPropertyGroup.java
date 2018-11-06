package elmar.sanderlings;

import java.util.Iterator;
import java.util.List;

public class TestPropertyGroup implements Iterable<TestProperty> {
	private List<TestProperty> properties;
	
	
	@Override
	public Iterator<TestProperty> iterator() {
		return properties.iterator();
	}
}
