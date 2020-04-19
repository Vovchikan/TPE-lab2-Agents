package supply.delivery;

import jade.core.behaviours.SimpleBehaviour;

public class FinishBehaviour extends SimpleBehaviour {

	DeliveryAgent myAgent;
	private boolean finished;
	
	public FinishBehaviour(DeliveryAgent a) {
		// TODO Auto-generated constructor stub
		this.myAgent = a;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println(myAgent.printRoute());
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
