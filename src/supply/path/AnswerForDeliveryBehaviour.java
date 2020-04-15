package supply.path;

import java.util.List;

import jade.core.behaviours.SimpleBehaviour;
import supply.cargo.CargoInfo;

public class AnswerForDeliveryBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private String deliveryAgentName;
	private List<CargoInfo> cargos;
	private boolean finished;
	
	public AnswerForDeliveryBehaviour() {
		// TODO Auto-generated constructor stub
	}

	public AnswerForDeliveryBehaviour(PathAgent a, String deliveryAgentName, List<CargoInfo> cargos) {
		super(a);
		// TODO Auto-generated constructor stub
		this.myAgent = a;
		this.deliveryAgentName = deliveryAgentName;
		this.cargos = cargos;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		myAgent.SendInfo(deliveryAgentName, new PathInfoForDelivery(cargos));
		myAgent.addBehaviour(new WaitDeliveryBehaviour(myAgent));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
