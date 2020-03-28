package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import supplyAgentCreator.*;

public class Main {
	public static void main(String[] args) {
		String filePath = GetFilePath(args);
		String parametr = null;
		try {
			parametr = GetGuiParametrForJade(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StartJadeGui(parametr);
	}
	
	private static String GetGuiParametrForJade(String filePath) 
			throws FileNotFoundException {
		String parametr = "";
		IAgentStringCreator creator = null;
	    File file = new File(filePath); 
	    Scanner sc = new Scanner(file); 
	  
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        if(ChooseCreator(creator, line) || creator == null)
	        	continue;
	        parametr += creator.CreatStringAgent(line);
	    }
		return parametr;
	}

	private static boolean ChooseCreator(IAgentStringCreator creator, String line) {
		if(line == "CargoAgent") {
			creator = new CargoAgentStringCreator();
			return true;
		}
		else if(line == "StoreAgent") {
			creator = new StoreAgentStringCreator();
			return true;
		}
		else if(line == "DeliveryAgent") {
			creator = new DeliveryAgentStringCreator();
			return true;
		}
		return false;
	}

	private static String GetFilePath(String[] args) {
		if(args == null || args.length == 0) {
			return "input.txt";
		}
		return null;
	}

	private static void StartJadeGui(String parametr) {
		if(parametr==null | parametr == "")
			parametr = "Frodo:supply.DeliveryAgent;RokovayaGora:supply.StoreAgent";
		jade.Boot.main(new String[] {"-gui", parametr});
	}
}
