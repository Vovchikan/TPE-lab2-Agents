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

}
