package supplyAgentCreator;

import supply.CargoAgent;
import supply.MyAgent;

public class CargoAgentStringCreator implements IAgentStringCreator {


	private MyAgent agent;

	public CargoAgentStringCreator() {
		agent = new CargoAgent();
	}
	
	@Override
	public String CreatStringAgent(String line) {
		return agent.CreateStringAgent(line);
	}

}
