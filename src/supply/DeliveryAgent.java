package supply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

@SuppressWarnings("serial")
public class DeliveryAgent extends Agent{
	private IVehicle vehicle;
	private List<CargoAgent> cargosList;
	
	protected void setup() {
		cargosList = new ArrayList<CargoAgent>();
		vehicle = new Vehicle();
		
		FillWithArgs(getArguments());
		
		addBehaviour(new B1(this));
	}
	
	class B1 extends SimpleBehaviour{
		public B1(Agent a)
		{
			super(a);
		}
		public void action() {
			String format = "My name is %s";
			System.out.println(String.format(format, myAgent.getLocalName() ) );
		}
		
		private boolean finished = false;
        public  boolean done() {  return finished; }
	}
	
	private void FillWithArgs(Object[] args) {
		vehicle.SetType(args[0]);
	}
	
	private void AddCargo(CargoAgent cargo) throws NotImplementedException {
		List<CargoAgent> tempList = new ArrayList<CargoAgent>();
		Collections.copy(tempList, cargosList);
		int totalWeight = 0;
		for (CargoAgent cargoAgent : cargosList) {
			totalWeight += cargoAgent.GetWeight();
		}
		if(vehicle.GetWeight() >= totalWeight+cargo.GetWeight())
			cargosList.add(cargo);
		else
			throw new NotImplementedException("Нет места.");
	}
}
