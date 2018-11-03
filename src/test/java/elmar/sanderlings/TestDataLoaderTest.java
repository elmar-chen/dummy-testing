package elmar.sanderlings;

import java.io.InputStream;

import org.dom4j.DocumentException;
import org.junit.Test;

public class TestDataLoaderTest {

	@Test
	public void test() throws DocumentException {
			InputStream source = this.getClass().getResourceAsStream("/elmar/sanderlings/rectangle.test.xml");
			new TestDataLoader().loadData(source);
			System.out.println(source);
		}

}
