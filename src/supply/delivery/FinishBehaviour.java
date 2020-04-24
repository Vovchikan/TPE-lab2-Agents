package supply.delivery;

import java.io.FileWriter;
import java.io.IOException;

import jade.core.behaviours.SimpleBehaviour;
import supply.path.RouteInfo;

public class FinishBehaviour extends SimpleBehaviour {

	DeliveryAgent myAgent;
	private boolean finished;
	
	public FinishBehaviour(DeliveryAgent a) {
		// TODO Auto-generated constructor stub
		this.myAgent = a;
	}

	@Override
	public void action() {
		String r = printRes();
		System.out.println(r);
		try(FileWriter writer = new FileWriter(myAgent.getLocalName()+".txt", false))
        {
            writer.write(r);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
		finished = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return finished;
	}

	private String printRes() {
		String res = String.format("\nRouteList for delivery: %s\n", myAgent.getLocalName());
		int i = 0;
		for (RouteInfo ri : myAgent.getRouteList()) {
			res += "Route number - "+Integer.toString(i++);
			res += myAgent.printRoute(ri);
			res += "\n"+"-".repeat(20)+"\n";
		}
		
		return res;
	}
}
