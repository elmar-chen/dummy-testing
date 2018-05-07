package test.framework.command.pattern;

import java.util.List;

public class CompositePatternComponent extends PatternComponent {
	List<PatternComponent> subComponents;

	public List<PatternComponent> getSubComponents() {
		return subComponents;
	}

	public void setSubComponents(List<PatternComponent> subComponents) {
		this.subComponents = subComponents;
	}
	
}
