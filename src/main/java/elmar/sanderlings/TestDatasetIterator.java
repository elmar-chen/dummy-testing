package elmar.sanderlings;

import java.util.Iterator;
import java.util.List;

public class TestDatasetIterator implements Iterator<TestProperty>{
	List<TestDataset> datasets;

	@Override
	public boolean hasNext() {
		throw new RuntimeException();
	}

	@Override
	public TestProperty next() {
		throw new RuntimeException();
	}

	public void addDataset(TestDataset dataset) {
		datasets.add(dataset);
	}
	
}
