package supply.delivery;

import java.io.FileWriter;
import java.io.IOException;

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
		try(FileWriter writer = new FileWriter(myAgent.getLocalName()+".txt", false))
        {
            writer.write(myAgent.printRoute());
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

}
