package test.framework;

import test.framework.command.pattern.PatternComponent;
import test.framework.model.ContextBasedTokenProvider;

public class ComputablePatternComponent extends PatternComponent {

	Class<? extends ContextBasedTokenProvider> providerClass;

	public ComputablePatternComponent(Class<? extends ContextBasedTokenProvider> providerClass) {
		this.providerClass = providerClass;
	}

}
