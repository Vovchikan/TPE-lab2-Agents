package supply;


import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

@SuppressWarnings("serial")
public class StoreAgent extends Agent {
	private ILocation location;
	private IDeliveryHours deliveryHours;
	
	protected void setup() {
		location = new LocationInt();
		deliveryHours = new DeliveryHours();
		FillWithArgs(getArguments());
		addBehaviour(new B1(this));
	}

	class B1 extends SimpleBehaviour {
		public B1(Agent a) {
			super(a);
		}

		public void action() {
			String format = "My name is %s";
			System.out.println(String.format(format, myAgent.getLocalName()));
		}

		private boolean finished = false;

		public boolean done() {
			return finished;
		}
	}
	
	private void FillWithArgs(Object[] args) {
		location.SetLocation(args[0]);
		deliveryHours.SetHours(args[1]);
	}
}
