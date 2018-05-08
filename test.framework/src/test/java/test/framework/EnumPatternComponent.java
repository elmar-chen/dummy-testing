package test.framework;

import test.framework.command.pattern.PatternComponent;

public class EnumPatternComponent extends PatternComponent {

	private Class<Enum<?>> clazz;

	public EnumPatternComponent(Class<Enum<?>> clazz) {
		this.clazz = clazz;
	}

	public Class<Enum<?>> getClazz() {
		return clazz;
	}

}
