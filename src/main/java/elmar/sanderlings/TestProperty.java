package elmar.sanderlings;

public class TestProperty extends TestDataElement{
	private final String name;
	private String value;

	
	public TestProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public TestProperty(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
