package test.framework.model;

import test.framework.TimeSpanUnit;
import test.framework.annotation.Pattern;

@Pattern({ "shortUnit: {amount}{unit}", "longUnit: {amount} {unit}" })
public class TimeSpan {
	private int amount;
	private TimeSpanUnit unit;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public TimeSpanUnit getUnit() {
		return unit;
	}

	public void setUnit(TimeSpanUnit unit) {
		this.unit = unit;
	}

	public long toMilliseconds() {
		return amount;
	}
}
