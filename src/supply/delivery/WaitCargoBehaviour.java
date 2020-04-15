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
		
		ACLMessage msg = null;
		if(myAgent.accumulatedMessages.peek()==null) {
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		 msg = myAgent.blockingReceive(m, 12000);
		} else msg = myAgent.accumulatedMessages.pop();
		var b = processingMessage(msg);
		
		myAgent.addBehaviour(b);
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}
	
	private SimpleBehaviour processingMessage(ACLMessage msg) {
		SimpleBehaviour behaviour;
		if(msg != null) {
			if(!msg.getSender().getLocalName().contains("CargoAgent"))
				throw new UnsupportedOperationException();
			IAgentInfo cargoInfo = new CargoInfo(msg.getSender().getLocalName());
			
			myAgent.ProcessingMessageContent(msg.getContent(), cargoInfo);
			System.out.println(myAgent.getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received.");
			
			var ci = (CargoInfo)cargoInfo;
			if( myAgent.CheckSpaceForCargo(ci) ) {
				myAgent.SendInfo(myAgent.GetPathAgentName(), myAgent.GetInfoForPath(ci));
				behaviour = new WaitPathBehaviour(myAgent, ci);
			}
			else
				behaviour = new UnAcceptedAnswerForCargo(myAgent, ci, "No place for cargo");
			
		}
		else {
			behaviour = new FinishBehaviour(myAgent);
		}
		return behaviour;
	}
}
