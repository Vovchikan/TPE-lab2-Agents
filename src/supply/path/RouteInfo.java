package supply.path;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Locale;

import supply.agent.AgentInfo;
import supply.cargo.CargoInfo;

public class RouteInfo extends AgentInfo {

	private ArrayList<RoutePoint> routePoints;
	private int lastTimeValue; // Конечное время, для прохода всего маршрута
	private Point lastPoint; // Конец маршрута
	private int startTimeValue;
	private String deliveryName;
	private String vw;
	private String vfw;

	public RouteInfo(int lastTimeValue) {
		routePoints = new ArrayList<RoutePoint>();
		this.lastTimeValue = lastTimeValue;
		this.startTimeValue = lastTimeValue;
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
		String shour = Integer.toString(RoutePoint.getHours(startTimeValue));
		String smins = RoutePoint.getStringMinuts(startTimeValue);
		// TODO Auto-generated method stub
		String res = String.format("\nVehicle weight: %s, Vehicle freeWeight: %s, Start time: %s:%s", vw, vfw, shour, smins);
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
				System.out.print("Проверка для "+routePoint.toString()+" прошла успешно"+"++".repeat(10));
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
	
	public static int CountRoadTime(Double speed, double roadLength) {
		int time = (int) Math.ceil(roadLength / speed);
		return time;
	}

	public static double CountRoadLength(Point start, Point end) {
		double length = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
		return length;
	}

	public void iniWeight(double weight, double fweight) {
		// TODO Auto-generated method stub
		vw = String.format(Locale.US, "%.2f", weight);
		vfw = String.format(Locale.US, "%.2f", fweight);
	}
}
