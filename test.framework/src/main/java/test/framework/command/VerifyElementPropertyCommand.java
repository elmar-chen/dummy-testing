package test.framework.command;

import test.framework.annotation.Pattern;
import test.framework.model.PageElement;


@Pattern("verify {element}'s {property} {operator} ({operand})")
public class VerifyElementPropertyCommand implements TestCaseCommand{

	
	PageElement element;
	
	@Pattern("")
	String property;
	@Pattern("")
	String operator;
	@Pattern("")
	String operand;
	
	@Override
	public void doStep() {
	}
}
