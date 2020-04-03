package supply.cargo;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.*;

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
		for (int i = 0; i < receiversNames.length; i++) {
			System.out.println(myAgent.getLocalName() + " is active.");
			SendMessage(receiversNames[i], myAgent.SendInfo());// send message
			
			MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			finished = ReceiveMessage(m, 12000);
			if(finished)
				break;
		}		
		if(!finished)
			System.out.println("Nikto menya ne vzyal");
		finished = true;
	}

	@Override
	public boolean done() {
		return finished;
	}
	
	private void SendMessage(String receiverName, String message) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent(message);
		
		msg.addReceiver(new AID(receiverName, AID.ISLOCALNAME));
		myAgent.send(msg);
	}
	
	private boolean ReceiveMessage(MessageTemplate m, int delay) {
		ACLMessage msg = myAgent.blockingReceive(m, delay);
		if(msg != null) {
			System.out.println(myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
			if("Accepted".equals(msg.getContent())) {
				System.out.println(myAgent.getLocalName() + ": " + msg.getSender().getLocalName() + " said that he will take me!");
				return true;
			}
			else {
				System.out.println(myAgent.getLocalName() + ": order from " + msg.getSender().getLocalName() + " was rejected - " + msg.getContent());
				return false;
			}
			
		}
		else {
			System.out.println(myAgent.getLocalName() + ": empty message was received");
			return false;
		}
	}

}
