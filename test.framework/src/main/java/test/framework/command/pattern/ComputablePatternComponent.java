package test.framework.command.pattern;

import test.framework.model.ContextBasedTokenProvider;

public class ComputablePatternComponent extends PatternComponent {

	Class<? extends ContextBasedTokenProvider> providerClass;

	public ComputablePatternComponent(Class<? extends ContextBasedTokenProvider> providerClass) {
		this.providerClass = providerClass;
	}

}
