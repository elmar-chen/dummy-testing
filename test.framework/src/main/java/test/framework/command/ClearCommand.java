package test.framework.command;

import test.framework.annotation.Pattern;

@Pattern("clear {object}")
public class ClearCommand implements TestCaseCommand {

	@Pattern("cookie|cache")
	private String object;

	@Override
	public void doStep() {

	}
}
