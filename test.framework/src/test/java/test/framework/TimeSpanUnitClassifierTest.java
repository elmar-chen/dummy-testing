package test.framework;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import test.framework.util.CollectionUtils;

public class TimeSpanUnitClassifierTest {

	@Test
	public void test() {
		TimeSpanUnitClassifier classifier = new TimeSpanUnitClassifier();
		Number[] pluralNums = { 0, 2, -2, 0.1, -0.1, 0.0, 1.0, -1.0, 1.5, -1.5, 2.0, -2.0 };
		Number[] singularlNums = { 1, -1 };
		for (Number number : singularlNums) {
			TimeSpanUnit[] units = classifier.getValidateEnums(number);
			assertTrue(number + " should be singular", CollectionUtils.indexOf(units, TimeSpanUnit.milliseconds) < 0);
			assertTrue(number + " should be singular", CollectionUtils.indexOf(units, TimeSpanUnit.s) >= 0);
			assertTrue(number + " should be singular", CollectionUtils.indexOf(units, TimeSpanUnit.second) >= 0);
		}
		for (Number number : pluralNums) {
			TimeSpanUnit[] units = classifier.getValidateEnums(number);
			assertTrue(number + " should be pural", CollectionUtils.indexOf(units, TimeSpanUnit.milliseconds) >= 0);
			assertTrue(number + " should be pural", CollectionUtils.indexOf(units, TimeSpanUnit.s) >= 0);
			assertTrue(number + " should be pural", CollectionUtils.indexOf(units, TimeSpanUnit.second) < 0);
		}
	}

}
