package test.framework.command.pattern;

import test.framework.ConsumeResult;

public class FixedPatternComponent extends PatternComponent {

	private String text;
	int currentIndex;

	public FixedPatternComponent(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public ConsumeResult consume(char charAt) {
		boolean match = text.charAt(currentIndex) == charAt;
		if(!match) {
			return ConsumeResult.FAIL_STOP;
		}
		return currentIndex == text.length() - 1 ? ConsumeResult.SUCCESS_STOP : ConsumeResult.SUCCESS_CONTINUE;
	}

}
