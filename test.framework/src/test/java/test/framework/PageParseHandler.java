package test.framework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import test.framework.model.PageElement;

public class PageParseHandler extends DefaultHandler{
	
	
	private Stack<PageElement> elementStack = new Stack<PageElement>();
	private PageElement lastElement = null;
	private String currentAttributeTag = null;
	public static final Set<String> ATTRIBUTE_ELEMENT_TAGS =new HashSet<String>( Arrays.asList("xpath", "selector", "id")); 
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("page".equals(qName) && !elementStack.isEmpty()) {
			throw new SAXException("<page> must be the root element.");
		}	
		if(!"page".equals(qName) && elementStack.isEmpty()) {
			throw new SAXException("The root must be element <page>.");
		}
		if(ATTRIBUTE_ELEMENT_TAGS.contains(qName)){
			currentAttributeTag = qName;
		} else {
			PageElement ele = new PageElement();
			ele.setVariables(parseVariables(attributes));
			ele.setType(qName);
			if(!elementStack.isEmpty()) {
				elementStack.peek().addSubElement(ele);
			}
			elementStack.push(ele);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(currentAttributeTag == null ) {
			lastElement = elementStack.pop();
		} else {
			currentAttributeTag = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(currentAttributeTag != null) {
			elementStack.peek().addVariables(currentAttributeTag, new String(ch));
		}
	}

	private Map<String, String> parseVariables(Attributes attributes) {
		Map<String, String> variables = new HashMap<String, String>();
		for(int i=0; i<attributes.getLength(); i++) {
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);
			variables.put(name, value);
		}
		return variables;
	}
	
	public PageElement getLastElement() {
		return lastElement;
	}
}
