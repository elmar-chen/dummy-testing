package elmar.sanderlings;

import java.util.List;

public class TestDataset extends TestDataElement  {
	private String clazz;

	public TestDataset() {
	}

	public TestDataset(String clazz) {
		this.clazz = clazz;
	}




	public TestDataset(List<TestDataElement> expanded) {
		subElments = expanded;
	}

	public String getClazz() {
		return clazz;
	}


}
