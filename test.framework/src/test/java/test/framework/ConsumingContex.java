package test.framework;

import java.util.List;
import java.util.Stack;

public class ConsumingContex {

	private Stack<List<ConsumingNode>> consumingTree = new Stack<>();
	private String input;
	public Stack<List<ConsumingNode>> getConsumingTree() {
		return consumingTree;
	}
	public void setConsumingTree(Stack<List<ConsumingNode>> consumingTree) {
		this.consumingTree = consumingTree;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}

}
