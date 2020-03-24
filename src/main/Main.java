package main;

public class Main {
	public static void main(String[] args) {
		String filePath = GetFilePath(args);
		String parametr = GetGuiParametrForJade(filePath);
		StartJadeGui(parametr);
	}
	
	private static String GetGuiParametrForJade(String filePath) {
		// TODO Auto-generated method stub
		return null;
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
	// Сделай фабрику агентов, в зависимости от строки, вызывать ту или иную фабрику
	// Класс main будет отправлять 
}
