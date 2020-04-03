package supply.delivery;

public class CargoInfo implements ICargoInfo {

	private double weight;
	private String destination;
	private String id;
	private String name;
	
	public CargoInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public double GetWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public String GetDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	@Override
	public String GetId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void CreateInfoFromMessage(String message) {
		var params = message.split(",");
		for(String param : params) {
			if(param.contains("destination"))
				destination = param.replaceAll("destination:", "");
			if(param.contains("weight"))
				weight = Double.parseDouble(param.replaceAll("weight:", ""));
		}
	}

	@Override
	public void PrintInConsole() {
		// TODO Auto-generated method stub
		System.out.print(name + " ");
	}

}
