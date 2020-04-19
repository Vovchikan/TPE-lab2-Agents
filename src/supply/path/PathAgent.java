package supply.path;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.List;
import java.util.TreeMap;

import jade.lang.acl.ACLMessage;
import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;
import supply.delivery.DeliveryInfoForPath;

public class PathAgent extends MyAgent {
	private static int count = 1;
	private String SecondName;
	public ArrayDeque<ACLMessage> accumulatedMessages;

	@Override
	protected int GetNumber() {
		// TODO Auto-generated method stub
		return count++;
	}

	@Override
	protected String[] GetParamsNames() {
		// TODO Auto-generated method stub
		return new String[] { "SecondName" };
	}

	@Override
	protected void FillWithArgs(Object[] args) {
		accumulatedMessages = new ArrayDeque<ACLMessage>();
		SecondName = (String) args[0];
	}

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		addBehaviour(new WaitDeliveryBehaviour(this));
	}

	@Override
	public IAgentInfo GetInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	private int CountRoadTime(Double speed, double roadLength) {
		int time = (int) Math.ceil(roadLength / speed);
		return time;
	}

	private double CountRoadLength(Point start, Point end) {
		double length = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
		return length;
	}

	public int ConvertTimeToMinuts(int hours, int minut) {
		return hours * 60 + minut;
	}

	public boolean cc(RouteInfo ri, double speed, Point end, int mintime, int maxtime, CargoInfo cargoInfo) {
		// Для начала нужно проверить, есть ли груз в существующем маршруте
		int i = ri.hasThisDestinationInRoute(cargoInfo.Destination);
		if (i >= 0) {
			ri.addExistDestination(cargoInfo, i);
			return true;
		} else {

			int time = ri.getLastTimeValue() + CountRoadTime(speed, CountRoadLength(ri.getLastPoint(), end));
			int delay = check(time, mintime, maxtime);
			if (delay >= 0) {
				ri.add(cargoInfo, delay, end, time + delay);
				return true;
			} else {
				return false;
			}
		}
	}

	private int check(int time, int mintime, int maxtime) {
		// TODO Auto-generated method stub
		if (time < mintime)
			return mintime - time;
		if (time >= mintime && time <= maxtime)
			return 0;
		else
			return -1;
	}
}
