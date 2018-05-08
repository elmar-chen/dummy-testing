package test.framework.command.pattern;

public class RegExpPatternComponent extends PatternComponent{
	private String regExp;

	public RegExpPatternComponent(String regExp) {
		this.regExp = regExp;
	}

	public String getRegExp() {
		return regExp;
	}

	
	public static RegExpPatternComponent delimiterComponent() {
		return new RegExpPatternComponent("\\s+");
	}
}
