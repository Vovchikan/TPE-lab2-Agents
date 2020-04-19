package supply.cargo;

import jade.core.behaviours.*;
import jade.lang.acl.*;
import supply.delivery.DeliveryInfoForCargo;

public class SendAndWaitBehaviour extends SimpleBehaviour {

	private boolean finished = false;
	private String[] receiversNames;
	private CargoAgent myAgent;

	public SendAndWaitBehaviour(CargoAgent cargoAgent) {
		super(cargoAgent);
		receiversNames = cargoAgent.DeliveriesNames;
		myAgent = cargoAgent;
	}

	@Override
	public void action() {
		DeliveryInfoForCargo info = null;
		for (int i = 0; i < receiversNames.length; i++) {
			System.out.println(myAgent.getLocalName() + " is trying to find free deliverAgent.");
			myAgent.SendInfo(receiversNames[i], myAgent.GetInfo());

			MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			info = ReceiveMessage(m, 12000);
			if (info == null)
				System.out.println(myAgent.getLocalName() + ": empty message was received.");
			else if (info.Success) {
				System.out.println(myAgent.getLocalName() + ": " + receiversNames[i] + " will drive me.");
				break;
			} else {
				String reason = info.Reason;
				System.out.println(myAgent.getLocalName() + ": " + receiversNames[i] + " WON'T DRIVE ME. REASON IS \""
						+ reason + "\".");
			}
		}
		if (info == null || !info.Success)
			System.out.println("Nikto menya ne vzyal");
		finished = true;
	}

	@Override
	public boolean done() {
		return finished;
	}

	private DeliveryInfoForCargo ReceiveMessage(MessageTemplate m, int delay) {
		ACLMessage msg = myAgent.blockingReceive(m, delay);
		if (msg != null) {
			System.out.println(
					myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
			DeliveryInfoForCargo info = DeliveryInfoForCargo.CreateFromString(msg.getContent());
			return info;

		}
		return null;
	}

}
