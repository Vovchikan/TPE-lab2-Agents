package supply.delivery;

import supply.agent.AgentInfo;

public class DeliveryInfoForCargo extends AgentInfo {

	public boolean Success;
	public String Reason;
	public boolean timeFail;
	public boolean weightFail;

	public DeliveryInfoForCargo(boolean success, String reason) {
		super();
		Success = success;
		Reason = reason;
		this.weightFail = false;
	}

	public static DeliveryInfoForCargo CreateFromString(String s) {
		var o = AgentInfo.CreateFromString(s);
		return (DeliveryInfoForCargo)o;
	}

}
