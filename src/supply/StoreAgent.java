package supply;


import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

@SuppressWarnings("serial")
public class StoreAgent extends MyAgent {
	private static int count = 1;
	
	@Override
	protected String[] GetParamsNames() {
		return new String[] {"Location", "Name", "DeliveryHours"};
	}
	
	@Override
	protected int GetNumber() {
		return count++;
	}
	
	private ILocation location;
	private IDeliveryHours deliveryHours;
	private String StoreName;
	
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
		StoreName = (String)args[1];
		deliveryHours.SetHours(args[2]);
	}
}
