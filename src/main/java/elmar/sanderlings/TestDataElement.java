package elmar.sanderlings;

import java.util.ArrayList;
import java.util.List;

public abstract class TestDataElement {
	protected List<TestDataElement> subElments = new ArrayList<>();

	public void addElement(TestDataElement element) {
		subElments.add(element);
	}

	public List<TestDataElement> getSubElments() {
		return subElments;
	}

	public void setSubElments(List<TestDataElement> subElments) {
		this.subElments = subElments;
	}
}
