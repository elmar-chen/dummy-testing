package test.framework.command.pattern;

import java.util.ArrayList;
import java.util.List;

public class CompositePatternComponent extends PatternComponent {

	public enum CompositeType {
		CHOOSE, SERIAL
	};

	private CompositeType compositeType;
	private List<PatternComponent> subComponents = new ArrayList<>();

	public CompositePatternComponent(CompositeType compositeType) {
		this.compositeType = compositeType;
	}

	public List<PatternComponent> getSubComponents() {
		return subComponents;
	}

	public void setSubComponents(List<PatternComponent> subComponents) {
		this.subComponents = subComponents;
	}

	public CompositeType getCompositeType() {
		return compositeType;
	}

}
