package supply;

import java.util.ArrayList;
import java.util.List;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

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
	
	private IVehicle vehicle;
	private List<CargoAgent> cargosList;
	
	@Override
	protected void FillWithArgs(Object[] args) {
		cargosList = new ArrayList<CargoAgent>();
		vehicle = new Vehicle();
		vehicle.SetType(args[0]);
	}
	
	private void AddCargo(CargoAgent cargo) throws NotImplementedException {
		if(CheckSpaceForCargo(cargo))
			cargosList.add(cargo);
		else
			throw new NotImplementedException("ƒобавить действи€ при нехватки места.");
	}
	
	private boolean CheckSpaceForCargo(CargoAgent cargo) {
		var totalWeight = cargo.GetWeight();
		for(CargoAgent c: cargosList) {
			totalWeight += c.GetWeight();
		}
		return totalWeight <= vehicle.GetWeight();
	}
}
