package supply;

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
	
	public double GetWeight() {
		return weight;
	}

	@Override
	protected void FillWithArgs(Object[] args) {
		destinationAddress = (String)args[0];
		weight = Double.parseDouble(args[1].toString());
	}
}
