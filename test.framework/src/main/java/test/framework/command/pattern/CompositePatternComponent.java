package test.framework.command.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositePatternComponent extends PatternComponent {

	private List<PatternComponent> subComponents = new ArrayList<>();

	public List<PatternComponent> getSubComponents() {
		return subComponents;
	}

	public void setSubComponents(List<PatternComponent> subComponents) {
		this.subComponents = subComponents;
	}

}
