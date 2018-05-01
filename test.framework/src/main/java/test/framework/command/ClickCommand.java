package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.model.PageElement;

@Pattern("click {element}")
public class ClickCommand implements TestCaseCommand{
	

	private PageElement element;
	public void doStep() {	
		element.resovle();
	}
}
