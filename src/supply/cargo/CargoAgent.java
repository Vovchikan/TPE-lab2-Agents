package supply.cargo;

import supply.MyAgent;

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

	@Override
	protected void FillWithArgs(Object[] args) {
		destinationAddress = (String)args[0];
		weight = Double.parseDouble(args[1].toString());
	}
	
	public String SendInfo() {
		String format = "weight:%f,destination:%s";
		return String.format(format, weight, destinationAddress);
	}
}
