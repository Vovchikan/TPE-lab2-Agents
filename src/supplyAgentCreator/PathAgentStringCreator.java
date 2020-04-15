package supplyAgentCreator;

import supply.agent.MyAgent;
import supply.path.PathAgent;

public class PathAgentStringCreator implements IAgentStringCreator {

	private MyAgent agent;
	
	public PathAgentStringCreator() {
		// TODO Auto-generated constructor stub
		agent = new PathAgent();
	}

	@Override
	public String CreatStringAgent(String line) {
		// TODO Auto-generated method stub
		return agent.CreateStringAgent(line);
	}

}
