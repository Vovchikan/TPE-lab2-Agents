package supply.path;

import jade.core.behaviours.SimpleBehaviour;

public class AnswerForDeliveryBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private String deliveryAgentName;
	private boolean finished;
	private RouteInfo ri;

	public AnswerForDeliveryBehaviour(PathAgent myAgent, RouteInfo ri, String deliveryAgentName) {
		super(myAgent);
		// TODO Auto-generated constructor stub
		this.myAgent = myAgent;
		this.deliveryAgentName = deliveryAgentName;
		this.ri = ri;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		myAgent.SendInfo(deliveryAgentName, new PathInfoForDelivery(ri));
		myAgent.addBehaviour(new WaitDeliveryBehaviour(myAgent));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
