package main;

public class ConsoleCommand implements ICommand {
	private static final String format = "%s /c start %s.exe /K \"%s\"";
	private String sreda;
	private String commanda;

	public ConsoleCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public ConsoleCommand(String sreda, String commanda) {
		super();
		this.sreda = sreda;
		this.commanda = commanda;
	}
	
	public void Execute() {
		try {
			var args = new Object[] {this.sreda, this.sreda, commanda};
			Runtime.getRuntime().exec(String.format(format, args));
		} catch (Exception e) {
			System.out.println("Wrong!");
			e.printStackTrace();
		}
	}
}
