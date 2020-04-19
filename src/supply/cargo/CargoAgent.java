package supply.cargo;

import supply.agent.IAgentInfo;
import supply.agent.MyAgent;

public class CargoAgent extends MyAgent{
	private static int count = 1;
	public String[] DeliveriesNames = new String[] {"DeliveryAgent1", "DeliveryAgent3", "DeliveryAgent2"};
	
	@Override
	protected String[] GetParamsNames() {
		return new String[] {"DestinationAddress", "Weight"};
	}
	
	@Override
	protected int GetNumber() {
		return count++;
	}
	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		addBehaviour(new SendAndWaitBehaviour(this));
	}
	
	private String destinationAddress;
	private double weight;
	
	public double GetWeight() {
		return weight;
	}
	
	public String GetDestinationAddress() {
		return destinationAddress;
	}
	
	@Override
	protected void FillWithArgs(Object[] args) {
		destinationAddress = (String)args[0];
		weight = Double.parseDouble(args[1].toString());
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return new CargoInfo(getLocalName(), weight, destinationAddress);
	}
}
