package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import supply.agent.IAgentInfo;
import supply.cargo.CargoInfo;

public class WaitCargoBehaviour extends SimpleBehaviour {

	private boolean finished = false;
	private boolean nextWait = true;
	private boolean noFreePlaceForCargo = false;
	private DeliveryAgent myAgent;

	public WaitCargoBehaviour(DeliveryAgent a) {
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
		var info = processingMessage(msg);
		
		if(info != null)
			myAgent.SendInfo(msg.getSender().getLocalName(), info);
		
		if(nextWait) {
			// ќжидать следующего запроса
			myAgent.addBehaviour(new WaitCargoBehaviour(myAgent));
		}
		finished = true;
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}
	
	private DeliveryInfoForCargo processingMessage(ACLMessage msg) {
		
		if(msg != null) {
			if(!msg.getSender().getLocalName().contains("CargoAgent"))
				throw new UnsupportedOperationException();
			IAgentInfo cargoInfo = new CargoInfo(msg.getSender().getLocalName());
			DeliveryInfoForCargo deliveryInfoForCargo = new DeliveryInfoForCargo();
			myAgent.ProcessingMessageContent(msg.getContent(), cargoInfo);
			System.out.println(myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
			if( myAgent.CheckSpaceForCargo((CargoInfo)cargoInfo) ) {
				System.out.println(myAgent.getLocalName() + ": " + msg.getSender().getLocalName() + " is taken at my vehicle!");
				// ƒобавить проверку на прохождение пути
				// ≈сли проверка не прошла, тогда вернуть false
				// ≈сли проверка прошла, добавить посылку в список
				myAgent.AddCargo((CargoInfo)cargoInfo);
				deliveryInfoForCargo.Permission("true");
			}
			else
				deliveryInfoForCargo.Permission("false,No place for cargo");
			return deliveryInfoForCargo;
			
		}
		else {
			System.out.println(myAgent.getLocalName() + ": empty message was received");
			System.out.print(myAgent.getLocalName() + " takes this cargos: ");
			myAgent.PrintAllCargos();
			nextWait = false;
			return null;
		}
	}
}
