package test.framework.util;

import java.text.ParseException;

import org.junit.Test;

public class PatternParserTest {

	@Test
	public void testParsePattern() throws ParseException {
		PatternParser parser = new PatternParser();
		ASTNode result = parser.parsePattern("wait (up to {time}|jk)|aaa for|{aaa} {element} to {behvavior} aa"); 
		System.out.println(result);
	}

}
