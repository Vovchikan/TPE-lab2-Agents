package supplyAgentCreator;

import java.util.Arrays;
import java.util.List;

import supply.DeliveryAgent;

public class DeliveryAgentStringCreator implements IAgentStringCreator {
	
	private static int count = 0;
	
	@Override
	public String CreatStringAgent(String line) {
		String FullClassName = DeliveryAgent.class.getName();
		String Name = GetName();
		String parametrs = ParseParametrs(line);
		
		return String.format("%s:%s(%s)", Name, FullClassName, parametrs);
	}

	private String ParseParametrs(String parametr) {
		var paramsAndValues = Arrays.asList(parametr.split(","));
		List<String> allValues = Arrays.asList();
		GetValueByName("Type", paramsAndValues, allValues);
		
		return String.join(",", allValues);
	}

	private void GetValueByName(String name, List<String> paramsAndValues, List<String> allValues) {
		String sep = ":";
		for(String paramAndValue: paramsAndValues) {
			if(paramAndValue.contains(name+sep)) {
				allValues.add(paramAndValue.replaceFirst(name+sep, ""));
				// Можно добавить удаление найденного элемента;
				return;
			}
		}
		
		throw new ArrayIndexOutOfBoundsException();
	}
	

	private String GetName() {
		return DeliveryAgent.class.getSimpleName() + count++;;
	}

}
