package test.framework;

public enum TimeSpanUnit {

	ms(1), millisecond(1, true, false), milliseconds(1, false, true),

	s(1000), sec(1000), second(1000, true, false), seconds(1000, false, true),

	min(60 * 1000), minute(60 * 1000, true, false), minutes(60 * 1000, false, true);

	private int timesMilliSecond;
	private boolean singular;
	private boolean plural;

	private TimeSpanUnit(int timesMilliSecond) {
		this(timesMilliSecond, true, true);
	}

	private TimeSpanUnit(int timesMilliSecond, boolean singular, boolean plural) {
		this.timesMilliSecond = timesMilliSecond;
		this.plural = plural;
		this.singular = singular;
	}

	public int getTimesMilliSecond() {
		return timesMilliSecond;
	}

	public boolean isPlural() {
		return plural;
	}

	public boolean isSingular() {
		return singular;
	}
}