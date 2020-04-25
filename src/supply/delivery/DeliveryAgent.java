package supply.delivery;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;

import jade.lang.acl.ACLMessage;
import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;
import supply.path.RouteInfo;

public class DeliveryAgent extends MyAgent {
	private static int count = 1;
	private int waitDelay = 10000;

	@Override
	protected String[] GetParamsNames() {
		return new String[] { "Type" };
	}

	@Override
	protected int GetNumber() {
		return count++;
	}

	@Override
	protected void setup() {
		super.setup();
		startWorkTime = 8 * 60 + 30;
		addBehaviour(new WaitCargoBehaviour(this));
	}

	private IVehicle vehicle;
	public ArrayDeque<ACLMessage> accumulatedMessages;
	private RouteInfo routeInfo;
	private ArrayList<RouteInfo> routeList;
	private int startWorkTime; // Начало рабочего дня
	public int UnaccAnswersInRow;

	@Override
	protected void FillWithArgs(Object[] args) {
		accumulatedMessages = new ArrayDeque<ACLMessage>();
		routeList = new ArrayList<>();
		vehicle = new Vehicle();
		routeInfo = null;
		vehicle.SetType(args[0]);
	}

	public void AddCargo(CargoInfo cargoInfo) {
		vehicle.PutCargo(cargoInfo.Weight);
	}

	public boolean CheckSpaceForCargo(CargoInfo cargoInfo) {
		return cargoInfo.Weight <= vehicle.GetFreeWeight();
	}

	@Override
	public IAgentInfo GetInfo() {
		return null;
	}

	public IAgentInfo GetInfoForPath(CargoInfo cargoInfo) {
		if (routeInfo == null) {
			routeInfo = new RouteInfo(this.getStartRouteTime());

		}
		DeliveryInfoForPath info = new DeliveryInfoForPath(vehicle, routeInfo, cargoInfo);

		return info;
	}

	private int getStartRouteTime() {
		int time;
		if (routeList == null || routeList.size() == 0)
			time = this.startWorkTime;
		else {
			var lr = routeList.get(routeList.size() - 1); // Последний составленный маршрут.
			time = lr.getLastTimeValue() + RouteInfo.CountRoadTime(vehicle.GetSpeed(),
					RouteInfo.CountRoadLength(lr.getLastPoint(), new Point(0, 0)));
		}
		return time;
	}

	public String GetPathAgentName() {
		return "PathAgent1";
	}

	public int getWaitDelay() {
		return waitDelay;
	}

	public void UpdateRoute(RouteInfo newRoat) {
		routeInfo = newRoat;
	}

	public String printRoute(RouteInfo routeInfo) {
		if (routeInfo != null)
			return routeInfo.toString();
		else
			return "I have no route";
	}

	public ArrayList<RouteInfo> getRouteList() {
		return routeList;
	}

	public boolean ifRouteNullOrEmpty() {
		return routeInfo == null || routeInfo.getRoutePoints().size() == 0;
	}

	public boolean RefreshRoute() {
		if (routeInfo != null) {
			routeInfo.iniWeight(vehicle.GetWeight(), vehicle.GetFreeWeight());
			routeList.add(routeInfo);
			routeInfo = null;
			vehicle.refresh();
			return true;
		} else {
			if (vehicle.GetWeight() != vehicle.GetFreeWeight())
				throw new UnsupportedOperationException();
		}
		return false;
	}
}
