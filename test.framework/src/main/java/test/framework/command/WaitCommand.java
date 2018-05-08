package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.model.TimeSpan;

@Pattern("wait (for) {time}")
public class WaitCommand implements TestCaseCommand {
	TimeSpan time;

	@Override
	public void doStep() {
		long ms = time.toMilliseconds();
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {

		}
	}
}
