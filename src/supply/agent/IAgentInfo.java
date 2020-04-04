package supply.agent;

import java.util.TreeMap;

public interface IAgentInfo {
	void CreateInfo(String[] params);
	String[] getInfo();
	TreeMap<String, String> getInfoTreeMap();
}
