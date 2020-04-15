package supply.path;

import java.util.List;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.cargo.CargoInfo;
import supply.delivery.DeliveryInfoForPath;

public class WaitDeliveryBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private boolean finished;

	public WaitDeliveryBehaviour() {
		// TODO Auto-generated constructor stub
	}

	public WaitDeliveryBehaviour(PathAgent a) {
		super(a);
		// TODO Auto-generated constructor stub
		myAgent = a;
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
			if (!msg.getSender().getLocalName().contains("DeliveryAgent")) {
				throw new UnsupportedOperationException();
			} else {
				var deliveryInfoForPath = new DeliveryInfoForPath();

				myAgent.ProcessingMessageContent(msg.getContent(), deliveryInfoForPath);
				System.out.println(
						myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
				
				List<CargoInfo> cargos = myAgent.FindNewRoute(deliveryInfoForPath);
				behaviour = new AnswerForDeliveryBehaviour(myAgent, msg.getSender().getLocalName(),
						cargos);
			}

		} else {
			behaviour = new FinishBehaviour(myAgent);
		}
		return behaviour;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
