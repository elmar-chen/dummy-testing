package elmar.sanderlings;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class TestDataLoaderTest {

	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException {
			InputStream source = this.getClass().getResourceAsStream("/elmar/sanderlings/rectangle.test.xml");
			new TestDataLoader().loadData(source);
			System.out.println(source);
		}

}
