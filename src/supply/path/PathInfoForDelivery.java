package supply.path;

import supply.agent.AgentInfo;

public class PathInfoForDelivery extends AgentInfo {
	
	private RouteInfo ri;
	public Boolean timeFail;

	public PathInfoForDelivery(RouteInfo ri) {
		this.ri = ri;
	}

	public RouteInfo getRoate() {
		// TODO Auto-generated method stub
		return ri;
	}

	public static PathInfoForDelivery CreateFromString(String s) {
		return (PathInfoForDelivery)AgentInfo.CreateFromString(s);
	}

	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return ri != null;
	}
}
