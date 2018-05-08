package test.framework.command.pattern;

public class FixedPatternComponent extends PatternComponent {

	private String text;

	public FixedPatternComponent(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
