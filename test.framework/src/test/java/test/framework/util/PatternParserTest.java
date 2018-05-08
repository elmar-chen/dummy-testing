package test.framework.util;

import java.text.ParseException;

import org.junit.Test;

public class PatternParserTest {

	@Test
	public void testParsePattern() throws ParseException {
		PatternParser parser = new PatternParser();
		ASTNode result = parser.parsePattern("wait (up to {time}|jk)|aaa for|{aaa} {element} to {behvavior} aa");

		System.out.println(result);

		// GROUP[TEXT(wait), DEL, CHOOSE[GROUP[TEXT(up), DEL, TEXT(to), DEL,
		// CHOOSE[VAR(time), TEXT(jk)]], TEXT(aaa)], DEL,
		// CHOOSE[TEXT(for), VAR(aaa)], DEL, VAR(element), DEL, TEXT(to), DEL,
		// VAR(behvavior), DEL, TEXT(aa)]

	}

}
