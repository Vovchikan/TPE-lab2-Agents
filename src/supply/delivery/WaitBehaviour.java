package supply.delivery;

import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitBehaviour extends SimpleBehaviour {

	private boolean finished = false;
	private boolean nextWait = true;
	private boolean noFreePlaceForCargo = false;
	private DeliveryAgent myAgent;

	public WaitBehaviour(DeliveryAgent a) {
		super(a);
		// TODO Auto-generated constructor stub
		myAgent = a;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println(myAgent.getLocalName() + " is active.");
		
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage msg = myAgent.blockingReceive(m, 12000);
		var res = ReceiveMessage(msg);
		if(res) {
			// Ответить отправителю об успехе
			SendAnswer(msg.getSender().getLocalName(), "Accepted");
		}
		else {
			if(noFreePlaceForCargo) {
				SendAnswer(msg.getSender().getLocalName(), "No place.");
				noFreePlaceForCargo = false;
			}
		}
		if(nextWait) {
			// Ожидать следующего запроса
			myAgent.addBehaviour(new WaitBehaviour(myAgent));
		}
		finished = true;
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}
	
	private boolean ReceiveMessage(ACLMessage msg) {
		
		if(msg != null) {
			ICargoInfo cargoInfo = new CargoInfo(msg.getSender().toString(), msg.getSender().getLocalName());
			cargoInfo.CreateInfoFromMessage(msg.getContent());
			System.out.println(myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
			if(myAgent.CheckSpaceForCargo(cargoInfo)) {
				System.out.println(myAgent.getLocalName() + ": " + msg.getSender().getLocalName() + " is taken at my vehicle!");
				// Добавить проверку на прохождение пути
				// Если проверка не прошла, тогда вернуть false
				// Если проверка прошла, добавить посылку в список
				myAgent.AddCargo(cargoInfo);
				return true;
			}
			else {
				return NoPlaceForCargo(msg);
			}
			
		}
		else {
			System.out.println(myAgent.getLocalName() + ": empty message was received");
			System.out.print(myAgent.getLocalName() + " takes this cargos: ");
			myAgent.PrintAllCargos();
			nextWait = false;
			return false;
		}
	}
	
	private boolean NoPlaceForCargo(ACLMessage msg) {
		System.out.println(myAgent.getLocalName() + ": I have no space for " + msg.getSender().getLocalName());
		noFreePlaceForCargo = true;
		return false;
	}
	
	private void SendAnswer(String receiverName, String message) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent(message);
		
		msg.addReceiver(new AID(receiverName, AID.ISLOCALNAME));
		myAgent.send(msg);
	}

}
