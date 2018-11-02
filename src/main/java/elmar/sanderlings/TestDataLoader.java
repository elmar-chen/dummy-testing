package elmar.sanderlings;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestDataLoader {
	public void loadData(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.parse(source);
		Element root = doc.getDocumentElement();
		TestData testData = new TestData(root.getAttribute("class"));
		parseChildren(testData, root);
		
	}	
	
	private void parseChildren(TestDataElement parent, Element root) {
		NamedNodeMap attrs = root.getAttributes();
		for(int i=0;i<attrs.getLength();i++) {
			Attr attr = (Attr) attrs.item(i);
			addAttributeProperty(parent, attr);
		}
		
		if(parent instanceof TestProperty) {
			TestProperty parentProp = (TestProperty) parent;
			String text = root.getTextContent();
			if(!StringUtils.isBlank(text)) {
				parentProp.setValue(text);
			}
		}
		StringBuffer sb = new StringBuffer();
		
		NodeList children = root.getChildNodes();
		for(int i=0; i<children.getLength();i++) {
			Node item = children.item(i);
		}
	}

	private void addAttributeProperty(TestDataElement parent, Attr attr) {
		String[] namePath = attr.getName().split(",");
		for(int i=0; i<namePath.length-1;i++) {
			TestProperty property = new TestProperty(namePath[i]);
			parent.addElement(property);
			parent = property;
		}
		String[] values = attr.getValue().split("\\|");
		for (String val : values) {
			TestProperty property = new TestProperty(namePath[namePath.length-1], val);
			parent.addElement(property);
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		System.out.println(TestDataLoader.class.getResource("/elmar/sanderlings/"));
//		new TestDataLoader().loadData(ClassLoader.getSystemResourceAsStream(name)ResourceAsStream("/elmar/sanderlings/rectangle.test.xml"));
	}
}
