package supply.delivery;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	protected void FillWithArgs(Object[] args) {
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
}
