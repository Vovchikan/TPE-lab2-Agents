package supply.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import supply.agent.IAgentInfo;
import supply.cargo.CargoInfo;

public class DeliveryInfoForPath implements IAgentInfo {
	
	public DeliveryInfoForPath() {
		
	}
	
	public DeliveryInfoForPath(List<CargoInfo> cargos, CargoInfo cargo) {
		super();
		this.cargos = cargos;
		this.cargo = cargo;
	}

	private List<CargoInfo> cargos;
	private CargoInfo cargo;
	private double speed;
	private String sepInCargo=" "; // Разделитель который разбивает информацию внутри каждого CargoInfo
	
	
	
	@Override
	public void CreateInfo(String[] params) {
		// TODO Auto-generated method stub
		if(params[0].toLowerCase() == "false")
			cargos=null;
		else {
			cargos = new ArrayList<CargoInfo>();
			for (int i = 2; i < params.length; i++) {
				CargoInfo ci = new CargoInfo();
				ci.CreateInfo(params[i].split(sepInCargo));
				cargos.add(ci);
			}
		}
		cargo = new CargoInfo();
		cargo.CreateInfo(params[1].split(sepInCargo));
		speed = Double.parseDouble(params[2]);

	}

	@Override
	public String[] getInfo() {
		var list = new ArrayList<String>();
		if(cargos == null || cargos.size() == 0) { 
			list.add("false");
			list.add(String.join(sepInCargo, cargo.getInfo()));
		}
		else {
			list.add("true");
			list.add(String.join(sepInCargo, cargo.getInfo()));
			for (CargoInfo ci : cargos) {
				list.add(String.join(sepInCargo, ci.getInfo()));
			}
		}
		list.add(Double.toString(speed));
		return list.toArray(new String[0]);
	}

	@Override
	public TreeMap<String, String> getInfoTreeMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TreeMap<String, CargoInfo> getCargoInfoTreeMap(){
		var map = new TreeMap<String, CargoInfo>();
		int i = 1;
		map.put("cargo", cargo);
		for (CargoInfo ci : cargos) {
			map.put(ci+Integer.toString(i), ci);
			i++;
		}
		return map;
	}
	
	public boolean HaveRoute() {
		return cargos != null && cargos.size() > 0;
	}
	
	public List<CargoInfo> GetRoute(){
		return cargos;
	}
	
	public double GetSpeed() {
		return speed;
	}
}
