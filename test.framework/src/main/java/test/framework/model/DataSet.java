package test.framework.model;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	private String name;
	private String category;
	private List<TestData> data = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<TestData> getData() {
		return data;
	}
	public void addData(TestData data) {
		this.data.add(data);
	}
	
}
