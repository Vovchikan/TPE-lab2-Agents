package supply.cargo;

import java.util.TreeMap;

import supply.agent.IAgentInfo;

public class CargoInfo implements IAgentInfo {

	private double weight;
	private String destination;
	private String name;
	
	public CargoInfo(String name) {
		this.name = name;
	}
	
	public CargoInfo(CargoAgent cargo) {
		this.name = cargo.getLocalName();
		this.weight = cargo.GetWeight();
		this.destination = cargo.GetDestinationAddress();
	}

	public Double GetWeight() {
		return weight;
	}
	
	public String GetDestination() {
		return destination;
	}
	
	public String GetName() {
		return name;
	}
	
	@Override
	public void CreateInfo(String[] params) {
		for(String param : params) {
			if(param.contains("destination"))
				destination = param.replaceAll("destination:", "");
			if(param.contains("weight"))
				weight = Double.parseDouble(param.replaceAll("weight:", ""));
		}
	}

	@Override
	public String[] getInfo() {
		String[] params = new String[] {"destination:"+destination, "weight:"+weight};
		return params;
	}

	@Override
	public TreeMap<String, String> getInfoTreeMap() {
		var map = new TreeMap<String, String>();
		map.put("destination", destination);
		map.put("weight", Double.toString(weight));
		return map;
	}

	public void PrintInConsole() {
		// Добавить реализацию
		
	}

}
