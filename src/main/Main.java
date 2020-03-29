package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import supplyAgentCreator.*;

public class Main {
	
	public static void main(String[] args) {
		String filePath = GetFilePath(args);
		String parametr = null;
		try {
			parametr = GetGuiParametrForJade(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StartJadeGui(parametr);
	}
	
	private static String GetGuiParametrForJade(String filePath)
			throws Exception {
		IAgentStringCreator creator = null;
		List<String> parametrs = new ArrayList<String>();
	    File file = new File(filePath); 
	    try(Scanner sc = new Scanner(file))
	    {
		    while (sc.hasNextLine()) {
		        String line = RemoveAllWhiteSpaces(sc.nextLine());
		        var res = ChooseCreator(line);
		        if(res != null) {
		        	creator = res;
		        	continue;
		        }
		        else if(creator != null) {
		        	var param = creator.CreatStringAgent(line);
			        if(param != null & !param.isEmpty())
			        	parametrs.add(param);
		        }
		        else {
		        	// do smth
		        	sc.close();
		        	throw new Exception("creator is null");
		        }
		    }
	    }
		return String.join(";", parametrs);
	}

	private static IAgentStringCreator ChooseCreator(String line) {
		IAgentStringCreator creator = null;
		if(line.equals("CargoAgent") ){
			creator = new CargoAgentStringCreator();
		}
		else if(line.equals("StoreAgent") ){
			creator = new StoreAgentStringCreator();
		}
		else if(line.equals("DeliveryAgent") ){
			creator = new DeliveryAgentStringCreator();
		}
		return creator;
	}

	private static String GetFilePath(String[] args) {
		if(args == null || args.length == 0) {
			return "input.txt";
		}
		return null;
	}

	private static void StartJadeGui(String parametr) {
		if(parametr==null || parametr == "")
			parametr = "Frodo:supply.DeliveryAgent;RokovayaGora:supply.StoreAgent";
		jade.Boot.main(new String[] {"-gui", parametr});
	}
	
	private static String RemoveAllWhiteSpaces(String str) {
		return str.replaceAll("\\s", "");
	}
}
