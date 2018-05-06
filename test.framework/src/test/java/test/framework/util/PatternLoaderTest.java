package test.framework.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import test.framework.command.ClearCommand;
import test.framework.command.TestCaseCommand;

public class PatternLoaderTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		List<Class<? extends TestCaseCommand>> classes = PatternLoader.loadCommandPrototypes();
		assertTrue(classes.contains(ClearCommand.class));
	}

}
