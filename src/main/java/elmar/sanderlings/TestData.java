package elmar.sanderlings;

import java.util.List;

public class TestData {
	private String clazz;
	private List<TestProperty> properties;
	private List<TestData> subset;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<TestProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<TestProperty> properties) {
		this.properties = properties;
	}

	public List<TestData> getSubset() {
		return subset;
	}

	public void setSubset(List<TestData> subset) {
		this.subset = subset;
	}

}
