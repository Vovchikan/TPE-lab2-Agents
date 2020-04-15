package supply.store;

import supply.agent.IAgentInfo;
import supply.agent.MyAgent;

public class StoreAgent extends MyAgent {
	private static int count = 1;
	
	@Override
	protected String[] GetParamsNames() {
		return new String[] {"Location", "Name", "DeliveryHours"};
	}
	
	@Override
	protected int GetNumber() {
		return count++;
	}
	
	private ILocation location;
	private IDeliveryHours deliveryHours;
	private String StoreName;

	@Override
	protected void FillWithArgs(Object[] args) {
		location = new LocationInt();
		deliveryHours = new DeliveryHours();
		location.SetLocation(args[0]);
		StoreName = (String)args[1];
		deliveryHours.SetHours(args[2]);
		
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
