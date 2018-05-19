package test.framework.command.pattern;

import test.framework.ConsumeResult;
import test.framework.ConsumingContex;

public abstract class PatternComponent {

	public abstract ConsumeResult consume(ConsumingContex context);
}
