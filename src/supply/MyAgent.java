package supply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jade.core.Agent;

public abstract class MyAgent extends Agent {
	
	protected abstract int GetNumber();
	
	protected abstract String[] GetParamsNames();
	
	protected abstract void FillWithArgs(Object[] args);
	
	@Override
	protected void setup() {
		FillWithArgs(getArguments());
	}
	
	public String CreateStringAgent(String line) {
		String FullClassName = this.getClass().getName();
		String Name = GetName();
		String parametrs = ParseParametrs(line, GetParamsNames());
		
		return String.format("%s:%s(%s)", Name, FullClassName, parametrs);
	}

	protected String ParseParametrs(String parametr, String[] paramsNames) {
		var paramsAndValues = Arrays.asList(parametr.split(","));
		List<String> allValues = new ArrayList<String>();
		for(String name: paramsNames) {
			GetValueByName(name, paramsAndValues, allValues);
		}
		
		return String.join(",", allValues);
	}

	protected void GetValueByName(String name, List<String> paramsAndValues, List<String> allValues) {
		String sep = ":";
		for(String paramAndValue: paramsAndValues) {
			if(paramAndValue.contains(name+sep)) {
				allValues.add(paramAndValue.replaceFirst(name+sep, ""));
				// Можно добавить удаление найденного элемента;
				return;
			}
		}
		
		throw new ArrayIndexOutOfBoundsException(name + paramsAndValues.toString());
	}

	protected String GetName() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + this.GetNumber();
	}

}
