package supply.delivery;

import java.util.ArrayList;
import java.util.List;

import supply.MyAgent;

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
		addBehaviour(new WaitBehaviour(this));
	}
	
	private IVehicle vehicle;
	private List<ICargoInfo> cargosInfo;
	
	@Override
	protected void FillWithArgs(Object[] args) {
		cargosInfo = new ArrayList<ICargoInfo>();
		vehicle = new Vehicle();
		vehicle.SetType(args[0]);
	}
	
	public void AddCargo(ICargoInfo cargoInfo) {
		cargosInfo.add(cargoInfo);
		vehicle.PutCargo(cargoInfo.GetWeight());
	}
	
	public boolean CheckSpaceForCargo(ICargoInfo cargoInfo) {
		return cargoInfo.GetWeight() <= vehicle.GetFreeWeight();
	}

	public void PrintAllCargos() {
		// TODO Auto-generated method stub
		for(ICargoInfo info : cargosInfo) {
			info.PrintInConsole();
		}
		System.out.println();
	}
}
