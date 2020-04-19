package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import supply.cargo.CargoInfo;

public class UnAcceptedAnswerForCargo extends SimpleBehaviour {

	private boolean finished;
	private DeliveryAgent myAgent;
	private CargoInfo cargoInfo;
	private String reason;
	
	public UnAcceptedAnswerForCargo(DeliveryAgent myAgent, CargoInfo ci, String reason) {
		// TODO Auto-generated constructor stub
		this.myAgent = myAgent;
		this.cargoInfo = ci;
		this.reason = reason;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		myAgent.SendInfo(cargoInfo.Name, new DeliveryInfoForCargo(false, reason));
		myAgent.addBehaviour(new WaitCargoBehaviour(myAgent));
		
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
