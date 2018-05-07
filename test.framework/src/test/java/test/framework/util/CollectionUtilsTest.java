package test.framework.util;

import java.util.Arrays;

import org.junit.Test;

public class CollectionUtilsTest {

	@Test
	public void testIndexOf() {
		CollectionUtils.indexOf(Arrays.asList(new String[] {}), "");
	}

}
