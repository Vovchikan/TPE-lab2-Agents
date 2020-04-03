package supplyAgentCreator;

import supply.MyAgent;
import supply.delivery.DeliveryAgent;

public class DeliveryAgentStringCreator implements IAgentStringCreator {
	
	private static int count = 0;
	private MyAgent agent;
	
	public DeliveryAgentStringCreator() {
		agent = new DeliveryAgent();
	}

	@Override
	public String CreatStringAgent(String line) {
		return agent.CreateStringAgent(line);
	}
}
