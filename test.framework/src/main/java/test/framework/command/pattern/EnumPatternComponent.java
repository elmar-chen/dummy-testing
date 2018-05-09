package test.framework.command.pattern;

public class EnumPatternComponent extends PatternComponent {

	private Class<Enum<?>> clazz;

	public EnumPatternComponent(Class<Enum<?>> clazz) {
		this.clazz = clazz;
	}

	public Class<Enum<?>> getClazz() {
		return clazz;
	}

}
