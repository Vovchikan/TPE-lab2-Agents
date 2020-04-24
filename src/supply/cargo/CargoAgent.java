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
		this.Wave = 0;
		addBehaviour(new SendAndWaitBehaviour(this));
	}
	
	private String destinationAddress;
	private double weight;
	private int Wave;
	
	public double GetWeight() {
		return weight;
	}
	
	public String GetDestinationAddress() {
		return destinationAddress;
	}
	
	public void IncreaseWaveByOne() {
		Wave++;
	}
	
	@Override
	protected void FillWithArgs(Object[] args) {
		destinationAddress = (String)args[0];
		weight = Double.parseDouble(args[1].toString());
	}

	@Override
	public IAgentInfo GetInfo() {
		return new CargoInfo(this.getLocalName(), weight, destinationAddress, Wave);
	}

}
