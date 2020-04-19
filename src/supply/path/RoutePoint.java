package supply.path;

import java.awt.Point;
import java.io.Serializable;

import supply.cargo.CargoInfo;

public class RoutePoint implements Serializable {

	private CargoInfo ci;
	private int delay;
	private Point lastPoint; // Конец маршрута
	private int lastTimeValue; // Конечное время, которое нужно для достижения точки маршрута
	

	public RoutePoint(CargoInfo ci, int delay) {
		this.ci = ci;
		this.delay = delay;
		lastPoint = new Point(0,0);
	}
	
	public RoutePoint(CargoInfo ci, int delay, Point lp, int ltv) {
		this.ci = ci;
		this.delay = delay;
		lastPoint = lp;
		lastTimeValue = ltv;
	}

	public CargoInfo getCi() {
		return ci;
	}

	public int getDelay() {
		return delay;
	}

	public Point getLastPoint() {
		return lastPoint;
	}
	
	public int getLastTimeValue() {
		return lastTimeValue;
	}

	@Override
	public String toString() {
		String format = "{Cargo: %s, Weight: %.2f, Location: (%d,%d) , Delay: %d, time: %d:%s}";
		
		int x = lastPoint.x;
		int y = lastPoint.y;
		int hours = getHours();
		String minuts = getMinuts();
		return String.format(format, ci.Name, ci.Weight, x, y, delay, hours, minuts);
	}

	private String getMinuts() {
		int res = lastTimeValue % 60;
		if(res == 0)
			return "00";
		if(res / 10 == 0)
			return "0"+Integer.toString(res);
		else
			return Integer.toString(res);
	}

	private int getHours() {
		return lastTimeValue / 60;
	}
}
