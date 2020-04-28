package supply.path;

import jade.core.behaviours.SimpleBehaviour;

public class AnswerForDeliveryBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private String deliveryAgentName;
	private boolean finished;
	private RouteInfo ri;
	private Boolean timeFail;

	public AnswerForDeliveryBehaviour(PathAgent myAgent, RouteInfo ri, String deliveryAgentName) {
		super(myAgent);
		// TODO Auto-generated constructor stub
		this.myAgent = myAgent;
		this.deliveryAgentName = deliveryAgentName;
		this.ri = ri;
		this.timeFail = false;
	}
	
	public AnswerForDeliveryBehaviour(PathAgent myAgent, RouteInfo ri, String deliveryAgentName, boolean timeFail) {
		this(myAgent, ri, deliveryAgentName);
		this.timeFail = timeFail;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		var i  =  new PathInfoForDelivery(ri);
		i.timeFail = this.timeFail;
		myAgent.SendInfo(deliveryAgentName, i);
		myAgent.addBehaviour(new WaitDeliveryBehaviour(myAgent));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
