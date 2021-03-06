package supply.store;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class FinishBehaviour extends SimpleBehaviour {

	private boolean finished;

	public FinishBehaviour(Agent a) {
		super(a);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		String format = "\n%s: I've got an EMPTY message, my work is done!\n";
		System.out.println(String.format(format, myAgent.getLocalName()));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
