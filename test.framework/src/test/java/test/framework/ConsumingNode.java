package test.framework;

import test.framework.command.pattern.PatternComponent;

public class ConsumingNode {

	private PatternComponent component;
	private int consumeIndex;
	
	public ConsumingNode(PatternComponent component, int consumeIndex) {
		this.component = component;
		this.consumeIndex = consumeIndex;
	}
	public PatternComponent getComponent() {
		return component;
	}
	public void setComponent(PatternComponent component) {
		this.component = component;
	}
	public int getConsumeIndex() {
		return consumeIndex;
	}
	public void setConsumeIndex(int consumeIndex) {
		this.consumeIndex = consumeIndex;
	}

	

}
