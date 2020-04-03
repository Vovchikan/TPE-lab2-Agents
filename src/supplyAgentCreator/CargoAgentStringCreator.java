package supplyAgentCreator;

import supply.MyAgent;
import supply.cargo.CargoAgent;

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
