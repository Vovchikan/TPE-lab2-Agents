package supply.path;

import java.util.List;

import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;
import supply.delivery.DeliveryInfoForPath;

public class PathAgent extends MyAgent {
	private static int count = 1;
	private String SecondName;

	@Override
	protected int GetNumber() {
		// TODO Auto-generated method stub
		return count++;
	}

	@Override
	protected String[] GetParamsNames() {
		// TODO Auto-generated method stub
		return new String[] {"SecondName"};
	}

	@Override
	protected void FillWithArgs(Object[] args) {
		SecondName = (String)args[0];
	}

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		addBehaviour(new WaitDeliveryBehaviour(this));
	}
	
	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CargoInfo> FindNewRoute(DeliveryInfoForPath deliveryInfoForPath) {
		// TODO Auto-generated method stub
		return null;
	}

}
