package supply.store;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitPathBehaviour extends SimpleBehaviour {

	private StoreAgent myAgent;
	private boolean finished;

	public WaitPathBehaviour(StoreAgent a) {
		super(a);
		// TODO Auto-generated constructor stub
		myAgent = a;
	}

	@Override
	public void action() {
		MessageTemplate m = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage msg = myAgent.blockingReceive(m, 12000);
		var b = processingBehaviour(msg);
		myAgent.addBehaviour(b);
		
		finished = false;
	}
	
	private SimpleBehaviour processingBehaviour(ACLMessage msg) {
		SimpleBehaviour bahaviour;
		if(msg != null) {
			String format = "\n%s: i've get message from %s!\n";
			System.out.println(String.format(format, myAgent.getLocalName(), msg.getSender().getLocalName()));
			myAgent.SendInfo(msg.getSender().getLocalName(), myAgent.GetInfo());
			bahaviour = new WaitPathBehaviour(myAgent);
		}else {
			bahaviour = new FinishBehaviour(myAgent);
			System.out.println(String.format("\n%s: I've got an EMPTY message, my work is done!\n", myAgent.getLocalName()));
		}
		
		return bahaviour;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
