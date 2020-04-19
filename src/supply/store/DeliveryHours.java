package supply.store;

public class DeliveryHours implements IDeliveryHours{

	private int min_hours;
	private int min_minuts;
	private int max_hours;
	private int max_minuts;

	@Override
	public void SetHours(Object obj) {
		// TODO Auto-generated method stub
		var s = (String)obj;
		var time = s.split("-");
		SetMinTime(time[0]);
		SetMaxTime(time[1]);
	}

	@Override
	public int GetMinTime() {
		return min_hours*60 + min_minuts;
	}

	@Override
	public int GetMaxTime() {
		return max_hours*60 + max_minuts;
	}
	
	private void SetMinTime(String str) {
		String [] parts = str.split("\\|");
		min_hours = Integer.parseInt(parts[0]);
		min_minuts = Integer.parseInt(parts[1]);
	}
	
	private void SetMaxTime(String str) {
		String [] parts = str.split("\\|");
		max_hours = Integer.parseInt(parts[0]);
		max_minuts = Integer.parseInt(parts[1]);
	}

}
