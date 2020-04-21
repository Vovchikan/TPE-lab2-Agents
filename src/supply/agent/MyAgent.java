package supply.agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public abstract class MyAgent extends Agent {
	
	protected abstract int GetNumber();
	
	protected abstract String[] GetParamsNames();
	
	protected abstract void FillWithArgs(Object[] args);
	
	public abstract IAgentInfo GetInfo();
	
	@Override
	protected void setup() {
		FillWithArgs(getArguments());
	}
	
	public String CreateStringAgent(String line) {
		String FullClassName = this.getClass().getName();
		String Name = GetName();
		String parametrs = JoinParametrsForJadeConsole(line, GetParamsNames());
		
		return String.format("%s:%s(%s)", Name, FullClassName, parametrs);
	}

	public void SendInfo(String receiversName, IAgentInfo info){
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		String content = null;
		try {
			content = info.serializeToString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg.setContent(content);
		
		msg.addReceiver(new AID(receiversName, AID.ISLOCALNAME));
		String sep = "##############################################################################\n";
		System.out.println(String.format("%sAgent: %s ------> Agent: %s\n%s", 
				sep, this.getLocalName(), receiversName, sep));
		this.send(msg);
	}
	
	protected String JoinParametrsForJadeConsole(String parametr, String[] paramsNames) {
		var paramsAndValues = Arrays.asList(parametr.split(","));
		List<String> allValues = new ArrayList<String>();
		for(String name: paramsNames) {
			GetValueByName(name, paramsAndValues, allValues);
		}
		
		return String.join(",", allValues);
	}

	protected void GetValueByName(String name, List<String> paramsAndValues, List<String> allValues) {
		String sep = ":";
		for(String paramAndValue: paramsAndValues) {
			if(paramAndValue.contains(name+sep)) {
				allValues.add(paramAndValue.replaceFirst(name+sep, ""));
				// Можно добавить удаление найденного элемента;
				return;
			}
		}
		
		throw new ArrayIndexOutOfBoundsException(name + paramsAndValues.toString());
	}

	protected String GetName() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName() + this.GetNumber();
	}

}
