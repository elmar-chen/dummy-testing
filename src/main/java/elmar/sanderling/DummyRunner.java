package elmar.sanderling;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class DummyRunner extends BlockJUnit4ClassRunner {

	public DummyRunner(Class<?> klass) throws InitializationError {
		super(klass);
		System.out.println(klass);
	}



}
