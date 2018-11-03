package elmar.sanderlings;

public class TestDataset extends TestDataElement  {
	private String clazz;

	public TestDataset() {
		this(null);
	}

	public TestDataset(String clazz) {
		this.clazz = clazz;
	}




	public String getClazz() {
		return clazz;
	}


}
