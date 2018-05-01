package test.framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import test.framework.annotation.CommandToken;

@CommandToken(PageElementTokenProvider.class)
public class PageElement {
	private Map<String, String> variables;
	private List<PageElement> subElements;
	private String type;
	
	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public void addVariables(String name, String value) {
		if(this.variables == null) this.variables = new HashMap<String, String>();
		this.variables.put(name, value);
	}
	
	public List<PageElement> getSubElements() {
		return subElements;
	}

	public void setSubElements(List<PageElement> subElements) {
		this.subElements = subElements;
	}
	
	public void addSubElement(PageElement ele) {
		if(subElements==null) subElements = new ArrayList<PageElement>();
		subElements.add(ele);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WebElement resovle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
