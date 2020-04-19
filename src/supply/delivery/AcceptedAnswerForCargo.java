package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import supply.cargo.CargoInfo;

public class AcceptedAnswerForCargo extends SimpleBehaviour {

	private boolean finished = false;
	private DeliveryAgent myAgent;
	private CargoInfo cargoInfo;
	
	public AcceptedAnswerForCargo() {
		// TODO Auto-generated constructor stub
	}

	public AcceptedAnswerForCargo(DeliveryAgent a, CargoInfo ci) {
		super(a);
		// TODO Auto-generated constructor stub
		this.cargoInfo = ci;
		this.myAgent = a;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		String format = "%s: %s is taken at my vehicle!";
		System.out.println(String.format(format, myAgent.getLocalName(), cargoInfo.Name));
		myAgent.SendInfo(cargoInfo.Name, new DeliveryInfoForCargo(true, null));
		myAgent.addBehaviour(new WaitCargoBehaviour(myAgent));
		
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
