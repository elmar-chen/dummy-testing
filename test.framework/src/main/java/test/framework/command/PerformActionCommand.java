package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.annotation.PatternToken;
import test.framework.model.TestActionProvider;

@Pattern("perform {action}")
public class PerformActionCommand implements TestCaseCommand {

	@PatternToken(TestActionProvider.class)
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void doStep() {

	}

}
