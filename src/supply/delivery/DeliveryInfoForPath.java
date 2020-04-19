package supply.delivery;

import supply.agent.AgentInfo;
import supply.cargo.CargoInfo;
import supply.path.RouteInfo;

public class DeliveryInfoForPath extends AgentInfo {
	
	private CargoInfo ci;
	private RouteInfo ri;
	private IVehicle v;
	
	public DeliveryInfoForPath(IVehicle v, RouteInfo ri, CargoInfo ci) {
		this.ri = ri;
		this.ci = ci;
		this.v = v;
	}
	
	public RouteInfo GetRouteInfo(){
		return ri;
	}
	
	public CargoInfo getCargoInfo() {
		return ci;
	}

	public static DeliveryInfoForPath CreateFromString(String s) {
		return (DeliveryInfoForPath)AgentInfo.CreateFromString(s);
	}

	public double getSpeed() {
		return v.GetSpeed();
	}
}
