package test.framework;

import java.util.ArrayList;
import java.util.List;

import test.framework.command.pattern.PatternComponent;

public class ConsumingContex {

	private List<PatternComponent> patterns = new ArrayList<>();
	private String input;
	int consumeIndex;

	public void setPatterns(List<PatternComponent> patterns) {
		this.patterns = patterns;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void consume() {
		for (int i = 0; i < input.length(); i++) {
			for (PatternComponent pattern : patterns) {
				ConsumeResult result = pattern.consume(input.charAt(i));

			}
		}
	}

}
