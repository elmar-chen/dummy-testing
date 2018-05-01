package test.framework.command;

import test.framework.annotation.CommandToken;
import test.framework.annotation.Pattern;
import test.framework.model.TestActionProvider;

@Pattern("perform {action}")
public class PerformActionCommand implements TestCaseCommand{
	
	@CommandToken(TestActionProvider.class)
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void doStep() {
		
	}
	
}
