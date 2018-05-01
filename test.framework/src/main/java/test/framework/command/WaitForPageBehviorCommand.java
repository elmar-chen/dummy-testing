package test.framework.command;

import org.openqa.selenium.WebElement;

import test.framework.annotation.Pattern;
import test.framework.model.TimeSpan;


@Pattern("wait (up to {time}) for page to {behavior}")
public class WaitForPageBehviorCommand implements TestCaseCommand{
	
	public static enum PageBehavior {load, reload};
	
	WebElement element;
	TimeSpan time;
	PageBehavior behavior;

	@Override
	public void doStep() {
		long ms = time.toMilliseconds();
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
