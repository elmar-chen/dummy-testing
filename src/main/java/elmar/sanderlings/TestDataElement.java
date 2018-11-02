package elmar.sanderlings;

import java.util.ArrayList;
import java.util.List;

public abstract class TestDataElement {
	private List<TestDataElement> subElments = new ArrayList<>();

	public void addElement(TestDataElement element) {
		subElments.add(element);
	}

}
