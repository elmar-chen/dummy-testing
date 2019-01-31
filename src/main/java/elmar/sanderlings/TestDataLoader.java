package elmar.sanderlings;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class TestDataLoader {
	public void loadData(InputStream source) throws DocumentException {

		SAXReader reader = new SAXReader();
		Document doc = reader.read(source);
		Element root = doc.getRootElement();
		TestDataset testData = new TestDataset(root.attributeValue("class"));
		parseChildren(testData, root);

		expandTestData(testData);
	}

	private void expandTestData(TestDataset testData) {
		List<TestDataElement> subElments = testData.getSubElments();
		List<TestDataElement> expandedSubElments = new ArrayList<>();
		for (TestDataElement testDataElement : subElments) {
			TestDataset dataSet;
			if (testDataElement instanceof TestProperty) {
				TestProperty testProperty = (TestProperty) testDataElement;
				List<TestProperty> expanded = expand(testProperty);
				dataSet = new TestDataset();
				dataSet.getSubElments().addAll(expanded);
				expandedSubElments.add(dataSet);
				expandTestData(dataSet);
			}
		}
		testData.setSubElments(expandedSubElments);
	}

	private List<TestProperty> expand(TestProperty testProperty) {
		String[] values = testProperty.getValue().split("\\|");
		List<TestProperty> expanded = new ArrayList<>();
		for (String val : values) {
			TestProperty property = new TestProperty(testProperty.getName(), val);
			property = expandByName(property);
			expanded.add(property);

		}
		return expanded;
	}

	private TestProperty expandByName(TestProperty property) {
		String[] namePath = property.getName().split("\\.");
		TestProperty parent = property;
		for (int i = 0; i < namePath.length - 1; i++) {
			TestProperty sub = new TestProperty(namePath[i]);
			parent.addElement(property);
			parent = sub;
		}
		return property;
	}

	private void parseChildren(TestDataElement parent, Element root) {
		for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
			Attribute attr = it.next();
			parent.addElement(new TestProperty(attr.getName(), attr.getValue()));
		}

		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element ele = it.next();
			String name = ele.getName();
			TestDataElement dataElement;
			if (name.equals("dataset")) {
				dataElement = new TestDataset();
			} else {
				dataElement = new TestProperty(name, ele.getTextTrim());

			}
			parent.addElement(dataElement);
			parseChildren(dataElement, ele);
		}
	}

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, DocumentException {
		System.out.println(TestDataLoader.class.getResource("/elmar/sanderlings/rectangle.test.xml"));
		InputStream is = TestDataLoader.class.getResourceAsStream("/elmar/sanderlings/rectangle.test.xml");
		new TestDataLoader().loadData(is);
	}
}
