package test.framework;

import java.util.ArrayList;
import java.util.List;

public class TimeSpanUnitClassifier implements EnumClassifier<TimeSpanUnit> {
	
	@Override
	public TimeSpanUnit[] getValidateEnums(Number amount){
		boolean plural = amount instanceof Double || Math.abs(amount.intValue())!=1;
		boolean singular = !plural;
		List<TimeSpanUnit> validTokens = new ArrayList<>();
		
		for(TimeSpanUnit unit : TimeSpanUnit.values()) {
			if(plural==unit.isPlural() || singular==unit.isSingular()) {
				validTokens.add(unit);
			}
		}
		return validTokens.toArray(new TimeSpanUnit[validTokens.size()]);
	}
}
