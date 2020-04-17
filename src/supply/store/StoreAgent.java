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
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
	}
	
	@Override
	protected void FillWithArgs(Object[] args) {
		location = new LocationInt();
		deliveryHours = new DeliveryHours();
		location.SetLocation(args[0]);
		StoreName = (String)args[1];
		deliveryHours.SetHours(args[2]);
		
	}
	
	@Override
	public String CreateStringAgent(String line) {
		// TODO Auto-generated method stub
		String FullClassName = this.getClass().getName();
		String Name = GetMyName(line);
		String parametrs = JoinParametrsForJadeConsole(line, GetParamsNames());
		
		return String.format("%s:%s(%s)", Name, FullClassName, parametrs);
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String GetMyName(String line) {
		var params = line.split(",");
		for (String param : params) {
			if(param.contains("Name"))
				return param.replaceAll("Name:", "");
		}
		throw new UnsupportedOperationException();
	}
}
