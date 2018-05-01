package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.model.PageElement;
import test.framework.model.TimeSpan;


@Pattern("wait (up to {time}) for {element} (to {behavior})")
public class WaitForElementBehviorCommand implements TestCaseCommand{
	PageElement element;
	TimeSpan time;
	
	@Pattern("appear|disappear")
	String behavior;

	@Override
	public void doStep() {
		long ms = time.toMilliseconds();
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
