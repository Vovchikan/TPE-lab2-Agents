package supply.delivery;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import jade.lang.acl.ACLMessage;
import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;
import supply.path.RouteInfo;

public class DeliveryAgent extends MyAgent{
	private static int count = 1;
	
	@Override
	protected String[] GetParamsNames() {
		return new String[] {"Type"};
	}
	
	@Override
	protected int GetNumber() {
		return count++;
	}
	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		addBehaviour(new WaitCargoBehaviour(this));
	}
	
	private IVehicle vehicle;
	public ArrayDeque<ACLMessage> accumulatedMessages;
	private RouteInfo routeInfo;
	
	@Override
	protected void FillWithArgs(Object[] args) {
		accumulatedMessages = new ArrayDeque<ACLMessage>();
		vehicle = new Vehicle();
		routeInfo = null;
		vehicle.SetType(args[0]);
	}
	
	public void AddCargo(CargoInfo cargoInfo) {
		vehicle.PutCargo(cargoInfo.Weight);
	}
	
	public boolean CheckSpaceForCargo(CargoInfo cargoInfo) {
		return cargoInfo.Weight <= vehicle.GetFreeWeight();
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public IAgentInfo GetInfoForPath(CargoInfo cargoInfo) {
		DeliveryInfoForPath info = new DeliveryInfoForPath(vehicle, routeInfo, cargoInfo);
		
		return info;
	}

	public String GetPathAgentName() {
		// TODO Auto-generated method stub
		return "PathAgent1";
	}

	public void UpdateRoute(RouteInfo newRoat) {
		// TODO Auto-generated method stub
		routeInfo = newRoat;
	}
}
