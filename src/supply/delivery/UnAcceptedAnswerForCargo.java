package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import supply.cargo.CargoInfo;

public class UnAcceptedAnswerForCargo extends SimpleBehaviour {

	private boolean finished;
	private DeliveryAgent myAgent;
	private CargoInfo cargoInfo;
	private String reason;
	private boolean timeFail;
	
	public UnAcceptedAnswerForCargo(DeliveryAgent myAgent, CargoInfo ci, String reason) {
		// TODO Auto-generated constructor stub
		this.myAgent = myAgent;
		this.cargoInfo = ci;
		this.reason = reason;
		this.timeFail = false;
	}
	
	public UnAcceptedAnswerForCargo(DeliveryAgent myAgent, CargoInfo ci, String reason, Boolean timeFail) {
		this(myAgent, ci, reason);
		this.timeFail = timeFail;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		myAgent.UnaccAnswersInRow++;
		var i = new DeliveryInfoForCargo(false, reason);
		i.timeFail = this.timeFail;
		i.weightFail = weightFail(cargoInfo, myAgent.getVehicle());
		myAgent.SendInfo(cargoInfo.Name, i);
		
		if(myAgent.UnaccAnswersInRow >= 10) {
			myAgent.RefreshRoute();
			myAgent.UnaccAnswersInRow = 0;
		}
		myAgent.addBehaviour(new WaitCargoBehaviour(myAgent));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

	public boolean weightFail(CargoInfo ci, IVehicle v) {
		return ci.Weight > v.GetWeight();
	}
	
}
