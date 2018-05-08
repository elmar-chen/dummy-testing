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

import test.framework.model.DataSet;
import test.framework.model.TestData;
import test.framework.model.TestSuit;

public class ScriptParseHandler extends DefaultHandler {

	private TestSuit suit = null;
	private Stack<Object> stack = new Stack<>();

	public static final Set<String> ATTRIBUTE_ELEMENT_TAGS = new HashSet<String>(
			Arrays.asList("xpath", "selector", "id"));

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement");

		if ("cases".equals(qName) && suit != null) {
			throw new SAXException("<cases> must be the root element.");
		}
		if (!"cases".equals(qName) && suit == null) {
			throw new SAXException("The root must be element <cases>.");
		}

		if ("cases".equals(qName)) {
			suit = new TestSuit();
			suit.setPage(attributes.getValue("page"));
			suit.setPortlet(attributes.getValue("portlet"));
			stack.push(suit);
		} else if ("dataset".equals(qName)) {
			DataSet dataSet = new DataSet();
			suit.addDataSet(dataSet);
			dataSet.setName(attributes.getValue("name"));
			dataSet.setCategory(attributes.getValue("category"));
			stack.push(dataSet);
		} else if ("data".equals(qName)) {
			DataSet dataSet = (DataSet) stack.peek();
			TestData data = new TestData();
			data.setName(attributes.getValue(":name"));
			data.setEntries(parseVariables(attributes));
			dataSet.addData(data);
			stack.push(data);
		} else if ("action".equals(qName)) {
			TestAction action = new TestAction();
			action.setName(attributes.getValue("name"));
			suit.addAction(action);
			stack.push(action);
		} else if ("case".equals(qName)) {
			TestCase caze = new TestCase();
			caze.setName(attributes.getValue("name"));
			caze.setDataSetName(attributes.getValue("use-data-set"));
			caze.setCleanUpActionName(attributes.getValue("clean-up"));
			suit.addCase(caze);
			stack.push(caze);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		stack.pop();
		System.out.println("endElement");

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("characters: start");
		if (stack.peek() instanceof TestAction) {
			TestAction action = (TestAction) stack.peek();
			String text = new String(ch, start, length);
			System.out.println("characters: text is: " + text);
			String[] lines = text.split("\n");
			for (String line : lines) {
				line = line.trim();
				if (line.length() == 0)
					continue;
				action.addCommandLine(line);
			}
		}
		System.out.println("characters: end");

	}

	private Map<String, String> parseVariables(Attributes attributes) {
		Map<String, String> variables = new HashMap<String, String>();
		for (int i = 0; i < attributes.getLength(); i++) {
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);
			variables.put(name, value);
		}
		return variables;
	}

	public TestSuit getSuit() {
		return suit;
	}
}
