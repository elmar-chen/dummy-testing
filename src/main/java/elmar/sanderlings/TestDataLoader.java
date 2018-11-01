package elmar.sanderlings;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestDataLoader {
	public void loadData(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = saxParserFactory.newSAXParser();
		parser.parse(source, new DefaultHandler() {
			
		});
		
	}
}
