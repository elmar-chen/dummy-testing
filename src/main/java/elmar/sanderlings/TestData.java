package elmar.sanderlings;

public class TestData extends TestDataElement  {
	private String clazz;

	public TestData() {
		this(null);
	}

	public TestData(String clazz) {
		this.clazz = clazz;
	}




	public String getClazz() {
		return clazz;
	}


}
