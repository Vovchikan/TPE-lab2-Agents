package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.cargo.CargoInfo;
import supply.path.PathInfoForDelivery;

public class WaitPathBehaviour extends SimpleBehaviour {

	DeliveryAgent myAgent;
	private boolean finished;
	private CargoInfo ci;

	public WaitPathBehaviour() {
		// TODO Auto-generated constructor stub
	}

	public WaitPathBehaviour(DeliveryAgent a, CargoInfo ci) {
		super(a);
		// TODO Auto-generated constructor stub
		myAgent = a;
		this.ci = ci;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage msg = myAgent.blockingReceive(m, 12000);

		var b = processingMessage(msg);

		myAgent.addBehaviour(b);
		finished = true;
	}

	private SimpleBehaviour processingMessage(ACLMessage msg) {
		// TODO Auto-generated method stub
		SimpleBehaviour behaviour;
		if (msg != null) {
			if (!msg.getSender().getLocalName().contains("PathAgent")) {
				myAgent.accumulatedMessages.add(msg);
				behaviour = new WaitPathBehaviour(myAgent, ci);
			} else {
				var pathInfoForDelivery = new PathInfoForDelivery();

				myAgent.ProcessingMessageContent(msg.getContent(), pathInfoForDelivery);
				System.out.println(
						myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");

				if (pathInfoForDelivery.isSuccess()) {
					myAgent.UpdateRoute(pathInfoForDelivery.GetRoat());
					behaviour = new AcceptedAnswerForCargo(myAgent, ci);
				} else
					behaviour = new UnAcceptedAnswerForCargo(myAgent, ci, "Can't find roat for you ;(");
			}

		} else {
			behaviour = new UnAcceptedAnswerForCargo(myAgent, ci, "No answer from pathAgent.".toUpperCase());
		}
		return behaviour;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
