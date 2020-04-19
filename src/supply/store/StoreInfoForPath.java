package supply.store;

import java.awt.Point;

import supply.agent.AgentInfo;

public class StoreInfoForPath extends AgentInfo {

	
	private Point loc;
	private int mint;
	private int maxt;

	public StoreInfoForPath(ILocation location, IDeliveryHours deliveryHours) {
		// TODO Auto-generated constructor stub
		loc = new Point(location.GetX(), location.GetY());
		mint = deliveryHours.GetMinTime();
		maxt = deliveryHours.GetMaxTime();
	}

	public Point getLoc() {
		return loc;
	}

	public int getMint() {
		return mint;
	}

	public int getMaxt() {
		return maxt;
	}

	public static StoreInfoForPath CreateFromString(String s) {
		var o = AgentInfo.CreateFromString(s);
		return (StoreInfoForPath)o;
	}
}
