package supply.delivery;

import java.util.TreeMap;

import supply.agent.IAgentInfo;

public class DeliveryInfoForCargo implements IAgentInfo {

	private boolean success;
	private String reason;
	
	@Override
	public void CreateInfo(String[] params) {
		for(String param : params) {
			if(param.contains("success"))
				success = Boolean.parseBoolean( ( param.replaceAll("success:", "") ) );
			if(param.contains("reason"))
				reason = param.replaceAll("reason:", "");
		}
	}

	@Override
	public String[] getInfo() {
		String[] params = new String[] {"success:"+Boolean.toString(success), "reason:"+reason};
		return params;
	}

	@Override
	public TreeMap<String, String> getInfoTreeMap() {
		var map = new TreeMap<String, String>();
		map.put("reason", reason);
		map.put("success", Boolean.toString(success));
		return map;
	}

	public void Permission(String string) {
		// TODO Auto-generated method stub
		if(string.toLowerCase().contains("true"))
			success = true;
		else {
			success = false;
			reason = string.split(",")[1];
		}
	}

	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return success;
	}

	public String getReason() {
		// TODO Auto-generated method stub
		return reason;
	}

}
