package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.cargo.CargoInfo;

public class WaitCargoBehaviour extends SimpleBehaviour {

	private boolean finished = false;
	private boolean nextWait = true;
	private boolean noFreePlaceForCargo = false;
	private DeliveryAgent myAgent;

	public WaitCargoBehaviour(DeliveryAgent a) {
		super(a);
		myAgent = a;
	}

	@Override
	public void action() {
		System.out.println(myAgent.getLocalName() + " is waiting message from cargo.");

		ACLMessage msg = null;
		SimpleBehaviour b = null;

		// ���� ��� ���������� ��������� - ��� �����
		if (myAgent.accumulatedMessages.peek() == null) {
			MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			msg = myAgent.blockingReceive(m, myAgent.getWaitDelay());
		} else
			msg = myAgent.accumulatedMessages.pop();

		b = processingMessage(msg);

		myAgent.addBehaviour(b);
		finished = true;
	}

	@Override
	public boolean done() {
		return finished;
	}

	private SimpleBehaviour processingMessage(ACLMessage msg) {
		SimpleBehaviour behaviour;
		if (msg != null) {
			if (!msg.getSender().getLocalName().contains("CargoAgent"))
				throw new UnsupportedOperationException();
			CargoInfo ci = CargoInfo.CreateFromString(msg.getContent());

			System.out.println(
					myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");

			if (!myAgent.CheckSpaceForCargo(ci)) {
				behaviour = new UnAcceptedAnswerForCargo(myAgent, ci, "No place for cargo.");
			} else {
				myAgent.SendInfo(myAgent.GetPathAgentName(), myAgent.GetInfoForPath(ci));
				behaviour = new WaitPathBehaviour(myAgent, ci);
			}

		} else {
			if (myAgent.ifRouteNullOrEmpty())
				behaviour = new FinishBehaviour(myAgent);
			else {
				myAgent.RefreshRoute();
				behaviour = new WaitCargoBehaviour(myAgent);
			}
		}
		return behaviour;
	}
}
