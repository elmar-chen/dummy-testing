package elmar.sanderlings;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	private List<TestProperty> expandTestData(TestDataset testData) {
		List<TestDataElement> subElments = testData.getSubElments();
		Map<String, Iterator<?>> iterators = new HashMap<String, Iterator<?>>();
		TestDatasetIterator datasetIter = new TestDatasetIterator();
		for (TestDataElement testDataElement : subElments) {
			if(testDataElement instanceof TestProperty) {
				TestProperty property = (TestProperty) testDataElement;
				TestPropertyIterator iterator = (TestPropertyIterator) iterators.get(property.getName());
				if(iterator==null){
					iterator = new TestPropertyIterator();
					iterators.put(property.getName(), iterator);
				}
				iterator.addTestProperty(property);
			} else {
				datasetIter.addDataset((TestDataset) testDataElement);
			}
		}
		return null;
	}

	private void parseChildren(TestDataElement parent, Element root) {
		for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
			Attribute attr = it.next();
			addAttributeProperty(parent, attr);
		}

		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element ele = it.next();
			String name = ele.getName();
			if (name.equals("dataset")) {
				TestDataset dataset = new TestDataset();
				parent.addElement(dataset);
				parseChildren(dataset, ele);
			} else {
				String values = ele.getTextTrim();
				String[] namePath = name.split("\\.");
				TestDataElement newParent = prepareParent(parent, namePath);

				for (String val : values.split("\\|")) {
					TestProperty property = new TestProperty(namePath[namePath.length - 1], val);
					newParent.addElement(property);
					parseChildren(property, ele);
				}
			}
		}
	}

	private void addAttributeProperty(TestDataElement parent, Attribute attr) {
		String[] namePath = attr.getName().split("\\.");
		parent = prepareParent(parent, namePath);
		String[] values = attr.getValue().split("\\|");
		for (String val : values) {
			TestProperty property = new TestProperty(namePath[namePath.length - 1], val);
			parent.addElement(property);
		}
	}

	private TestDataElement prepareParent(TestDataElement parent, String[] namePath) {
		for (int i = 0; i < namePath.length - 1; i++) {
			TestProperty property = new TestProperty(namePath[i]);
			parent.addElement(property);
			parent = property;
		}
		return parent;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		System.out.println(TestDataLoader.class.getResource("/elmar/sanderlings/"));
//		new TestDataLoader().loadData(ClassLoader.getSystemResourceAsStream(name)ResourceAsStream("/elmar/sanderlings/rectangle.test.xml"));
	}
}
