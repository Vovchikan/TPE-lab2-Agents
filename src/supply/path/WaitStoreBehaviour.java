package supply.path;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.cargo.CargoInfo;
import supply.store.StoreInfoForPath;

public class WaitStoreBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private boolean finished;
	private RouteInfo ri;
	private double speed;
	private CargoInfo cargoInfo;
	private String deliveryAgentName;
	
	public WaitStoreBehaviour(PathAgent a, RouteInfo ri, double speed, CargoInfo cargoInfo, String deliveryAgentName) {
		this(a);
		this.ri = ri;
		this.speed = speed;
		this.cargoInfo = cargoInfo;
		this.deliveryAgentName = deliveryAgentName;
	}

	public WaitStoreBehaviour(PathAgent a) {
		super(a);
		// TODO Auto-generated constructor stub
		myAgent = a;
	}

	@Override
	public void action() {
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage msg = myAgent.blockingReceive(m, 12000);

		var b = processingMessage(msg);

		myAgent.addBehaviour(b);
		finished = true;
	}

	private SimpleBehaviour processingMessage(ACLMessage msg) {
		SimpleBehaviour behaviour;
		if (msg != null) {
			if (msg.getSender().getLocalName().contains("DeliveryAgent")) {
				myAgent.accumulatedMessages.add(msg);
				behaviour = new WaitStoreBehaviour(myAgent, ri, speed, cargoInfo, deliveryAgentName);
			} else {
				StoreInfoForPath si = StoreInfoForPath.CreateFromString(msg.getContent());

				System.out.println(
						myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
				boolean res = myAgent.cc(ri, speed, si.getLoc(), si.getMint(), si.getMaxt(), cargoInfo);
				if(res)
					behaviour = new AnswerForDeliveryBehaviour(myAgent, ri, deliveryAgentName);
				else
					behaviour = new AnswerForDeliveryBehaviour(myAgent, null, deliveryAgentName, findTimeFail(ri, si.getMaxt()));
			}

		} else behaviour = new FinishBehaviour(myAgent);
		
		return behaviour;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

	private boolean findTimeFail(RouteInfo ri, int maxTime) {
		int routeTimeStart = ri.getLastTimeValue();
		return routeTimeStart > maxTime;
	}
}
