package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.model.PageElement;

@Pattern("enter|input {text} in {element}")
public class EnterTextCommand implements TestCaseCommand {
	PageElement element;
	@Pattern("")
	String text;

	@Override
	public void doStep() {
		// element.sendKeys(text);
	}
}
