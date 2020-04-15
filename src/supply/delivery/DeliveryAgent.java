package supply.delivery;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import jade.lang.acl.ACLMessage;
import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;

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
	private List<CargoInfo> cargosInfo;
	public ArrayDeque<ACLMessage> accumulatedMessages;
	
	@Override
	protected void FillWithArgs(Object[] args) {
		accumulatedMessages = new ArrayDeque<ACLMessage>();
		cargosInfo = new ArrayList<CargoInfo>();
		vehicle = new Vehicle();
		vehicle.SetType(args[0]);
	}
	
	public void AddCargo(CargoInfo cargoInfo) {
		cargosInfo.add(cargoInfo);
		vehicle.PutCargo(cargoInfo.GetWeight());
	}
	
	public boolean CheckSpaceForCargo(CargoInfo cargoInfo) {
		return cargoInfo.GetWeight() <= vehicle.GetFreeWeight();
	}

	public void PrintAllCargos() {
		// TODO Auto-generated method stub
		for(CargoInfo info : cargosInfo) {
			info.PrintInConsole();
		}
		System.out.println();
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public IAgentInfo GetAnswerInfoForCargo(String string) {
		// TODO Auto-generated method stub
		DeliveryInfoForCargo info = new DeliveryInfoForCargo();
		info.Permission(string);
		return info;
	}

	public IAgentInfo GetInfoForPath(CargoInfo cargoInfo) {
		DeliveryInfoForPath info = new DeliveryInfoForPath(cargosInfo, cargoInfo);
		
		return info;
	}

	public String GetPathAgentName() {
		// TODO Auto-generated method stub
		return "PathAgent1";
	}

	public void UpdateRoute(List<CargoInfo> newRoat) {
		// TODO Auto-generated method stub
		cargosInfo = newRoat;
	}
}
