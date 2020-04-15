package supply.cargo;

import java.util.TreeMap;

import supply.agent.IAgentInfo;

public class CargoInfo implements IAgentInfo {

	private double weight;
	private String destination;
	private String name;
	private int delay; // in minuts
	
	public CargoInfo() {
		this.delay = 0;
	}
	
	public CargoInfo(String name) {
		this();
		this.name = name;
	}
	
	public CargoInfo(CargoAgent cargo) {
		this();
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
	
	public int GetDelay() {
		return delay;
	}
	
	public void SetDelay(double delay) {
		this.delay = (int)Math.ceil(delay);
	}
	
	@Override
	public void CreateInfo(String[] params) {
		for(String param : params) {
			if(param.contains("destination"))
				destination = param.replaceAll("destination:", "");
			if(param.contains("weight"))
				weight = Double.parseDouble(param.replaceAll("weight:", ""));
			if(param.contains("delay"))
				delay = Integer.parseInt(param.replaceAll("delay:", ""));
			if(param.contains("name"))
				name = param.replaceAll("name:", "");
		}
	}

	@Override
	public String[] getInfo() {
		String[] params = new String[] {"destination:"+destination, "weight:"+weight, "delay:"+delay, "name:"+name};
		return params;
	}

	@Override
	public TreeMap<String, String> getInfoTreeMap() {
		var map = new TreeMap<String, String>();
		map.put("destination", destination);
		map.put("weight", Double.toString(weight));
		map.put("delay", Integer.toString(delay));
		map.put("name", name);
		return map;
	}

	public void PrintInConsole() {
		// Добавить реализацию
		
	}

}
