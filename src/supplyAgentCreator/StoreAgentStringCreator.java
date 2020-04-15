package supplyAgentCreator;

import supply.agent.MyAgent;
import supply.store.StoreAgent;

public class StoreAgentStringCreator implements IAgentStringCreator {

	private MyAgent agent;
	
	public StoreAgentStringCreator() {
		agent = new StoreAgent();
	}
	
	@Override
	public String CreatStringAgent(String line) {
		return agent.CreateStringAgent(line);
	}

}
