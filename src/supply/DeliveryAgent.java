package supply;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class DeliveryAgent extends Agent{
	public static void main(String[] args) {
		
	}
	protected void setup() {
		addBehaviour(new B1(this));
	}
	
	class B1 extends SimpleBehaviour{
		public B1(Agent a)
		{
			super(a);
		}
		public void action() {
			String format = "My name is %s";
			System.out.println(String.format(format, myAgent.getLocalName() ) );
		}
		
		private boolean finished = false;
        public  boolean done() {  return finished; }
	}
	
}
