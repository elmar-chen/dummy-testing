package test.framework;

import java.util.ArrayList;
import java.util.List;

public class TestAction {
	private String name;
	private List<String> commandLines = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCommandLines() {
		return commandLines;
	}

	public void addCommandLine(String commandLine) {
		commandLines.add(commandLine);
	}
}
