package supply.path;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import supply.agent.IAgentInfo;
import supply.cargo.CargoInfo;

public class PathInfoForDelivery implements IAgentInfo {
	private List<CargoInfo> cargos;
	private String sepInCargoInfo=" ";

	public PathInfoForDelivery() {
	}
	
	public PathInfoForDelivery(List<CargoInfo> cargos) {
		this.cargos = cargos;
	}

	@Override
	public void CreateInfo(String[] params) {
		// TODO Auto-generated method stub
		if(params[0].toLowerCase() == "false") {
			cargos = null;
		}
		if(params[0].toLowerCase() == "true") {
			cargos = new ArrayList<CargoInfo>();
			for (int i = 0; i < params.length; i++) {
				var ci = new CargoInfo();
				ci.CreateInfo(params[i].split(sepInCargoInfo));
			}
		}
	}

	@Override
	public String[] getInfo() {
		List<String> list = new ArrayList<String>();
		if(cargos == null)
			list.add("false");
		else {
			list.add("true");
			for (CargoInfo ci : cargos) {
				list.add(String.join(sepInCargoInfo, ci.getInfo()));
			}
		}
		return list.toArray(new String[0]);
	}

	@Override
	public TreeMap<String, String> getInfoTreeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return cargos!=null && cargos.size() > 0;
	}

	public List<CargoInfo> GetRoat() {
		// TODO Auto-generated method stub
		return cargos;
	}

	
}
