package supply.path;

import jade.core.behaviours.SimpleBehaviour;

public class FinishBehaviour extends SimpleBehaviour {

	PathAgent myAgent;
	private boolean finished;
	
	public FinishBehaviour() {
		// TODO Auto-generated constructor stub
	}

	public FinishBehaviour(PathAgent a) {
		super(a);
		// TODO Auto-generated constructor stub
		this.myAgent = a;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		String format = "\n%s: my work is done. I'm going to sleep!\n";
		System.out.println(String.format(format, myAgent.getLocalName()));
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

}
