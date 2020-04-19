package supply.path;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.delivery.DeliveryInfoForPath;

public class WaitDeliveryBehaviour extends SimpleBehaviour {

	private PathAgent myAgent;
	private boolean finished;

	public WaitDeliveryBehaviour(PathAgent a) {
		super(a);
		myAgent = a;
	}

	@Override
	public void action() {
		ACLMessage msg = null;
		
		// Если нет отложенных сообщений - ждём новое
		if(myAgent.accumulatedMessages.peek()==null) {
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		 msg = myAgent.blockingReceive(m, 120000);
		} else msg = myAgent.accumulatedMessages.pop();
		
		var b = processingMessage(msg);
		myAgent.addBehaviour(b);
		finished = true;
	}

	private SimpleBehaviour processingMessage(ACLMessage msg) {
		SimpleBehaviour behaviour;
		if (msg != null) {
			if (!msg.getSender().getLocalName().contains("DeliveryAgent")) {
				throw new UnsupportedOperationException();
			} else {
				var di = DeliveryInfoForPath.CreateFromString(msg.getContent());

				System.out.println(
						myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
				String storeName = di.getCargoInfo().Destination;
				myAgent.SendInfo(storeName, new PathInfoForStore());
				behaviour = new WaitStoreBehaviour(myAgent, di.GetRouteInfo(), di.getSpeed(), 
						di.getCargoInfo(), msg.getSender().getLocalName());
			}

		} else {
			behaviour = new FinishBehaviour(myAgent);
		}
		return behaviour;
	}

	@Override
	public boolean done() {
		return finished;
	}

}
