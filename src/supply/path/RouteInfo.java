package supply.path;

import java.awt.Point;
import java.util.ArrayList;

import supply.agent.AgentInfo;
import supply.cargo.CargoInfo;

public class RouteInfo extends AgentInfo {

	private ArrayList<RoutePoint> routePoints;
	private int lastTimeValue; // Конечное время, для прохода всего маршрута
	private Point lastPoint; // Конец маршрута
	private String deliveryName;

	public RouteInfo() {
		routePoints = new ArrayList<RoutePoint>();
		lastTimeValue = 8 * 60 + 30; // 8 hours and 30 minuts
		lastPoint = new Point(0, 0);
	}

	public void add(CargoInfo cargoInfo, int delay, Point lp, int ltv) {
		routePoints.add(new RoutePoint(cargoInfo, delay, lp, ltv));
		lastTimeValue = ltv;
		lastPoint = lp;
	}

	public ArrayList<RoutePoint> getRoutePoints() {
		return routePoints;
	}

	public int getLastTimeValue() {
		return lastTimeValue;
	}

	public Point getLastPoint() {
		return lastPoint;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public static RouteInfo CreateFromString(String s) {
		return (RouteInfo) AgentInfo.CreateFromString(s);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String res = "Nachalo";
		int i = 1;
		for (RoutePoint routePoint : routePoints) {
			res += "\n" + i++ + ". ";
			res += routePoint.toString();
		}
		return res;
	}

	public int hasThisDestinationInRoute(String destination) {
		int i = 0;
		for (RoutePoint routePoint : routePoints) {
			if (routePoint.getCi().Destination == destination) {
				System.out.print("Проверка для "+routePoint.toString()+" прошла успешно ++++++++++++++++++++++++++++++++");
				return i;
			}
			i++;
		}
		return -1;
	}

	public void addExistDestination(CargoInfo cargoInfo, int index) {
		routePoints.add(index + 1, new RoutePoint(cargoInfo, 0, routePoints.get(index).getLastPoint(),
				routePoints.get(index).getLastTimeValue()));

	}
}
