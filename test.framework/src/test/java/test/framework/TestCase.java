package test.framework;

public class TestCase extends TestAction {
	private String dataSetName;
	private String cleanUpActionName;

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public String getCleanUpActionName() {
		return cleanUpActionName;
	}

	public void setCleanUpActionName(String cleanUpActionName) {
		this.cleanUpActionName = cleanUpActionName;
	}

}
