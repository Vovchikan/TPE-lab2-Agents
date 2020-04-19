package supply.cargo;

import supply.agent.AgentInfo;

public class CargoInfo extends AgentInfo{

	private final static long serialVersionUID = 1;
	
	public double Weight;
	public String Destination;
	public String Name;
	
	public CargoInfo(String name, double weight, String destination) {
		this.Name = name;
		this.Weight = weight;
		this.Destination = destination;
	}

	public void PrintInConsole() {
		// Добавить реализацию
		
	}

	public static CargoInfo CreateFromString(String s) {
		var o = AgentInfo.CreateFromString(s);
		return (CargoInfo)o;
	}
}
