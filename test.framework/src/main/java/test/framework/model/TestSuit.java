package test.framework.model;

import java.util.ArrayList;
import java.util.List;

import test.framework.TestAction;
import test.framework.TestCase;

public class TestSuit {
	private List<DataSet> dataSets = new ArrayList<>();
	private List<TestAction> actions = new ArrayList<>();
	private List<TestCase> cases = new ArrayList<>();
	
	private String page;
	private String portlet;
	
	public List<DataSet> getDataSets() {
		return dataSets;
	}
	public void addDataSet(DataSet dataSet) {
		this.dataSets.add(dataSet);
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPortlet() {
		return portlet;
	}
	public void setPortlet(String portlet) {
		this.portlet = portlet;
	}
	public List<TestAction> getActions() {
		return actions;
	}
	public void addAction(TestAction action) {
		this.actions.add(action);
	}
	public List<TestCase> getCases() {
		return cases;
	}
	public void addCase(TestCase caze) {
		this.cases.add(caze);
	}
	public void setDataSets(List<DataSet> dataSets) {
		this.dataSets = dataSets;
	}

	
}
