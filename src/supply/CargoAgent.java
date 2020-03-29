package supply;

import jade.core.Agent;

@SuppressWarnings("serial")
public class CargoAgent extends MyAgent{
	private static int count = 1;
	
	@Override
	protected String[] GetParamsNames() {
		return new String[] {"DestinationAddress", "Weight"};
	}
	
	@Override
	protected int GetNumber() {
		return count++;
	}
	
	private String destinationAddress;
	private double weight;
	
	protected void setup() {
		FillWithArgs(getArguments());
		
	}
	
	private void FillWithArgs(Object[] args) {
		destinationAddress = (String)args[0];
		weight = (double)args[1];
	}
	
	public double GetWeight() {
		return weight;
	}
}
