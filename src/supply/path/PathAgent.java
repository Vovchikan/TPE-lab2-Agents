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
		return new String[] {"SecondName"};
	}

	@Override
	protected void FillWithArgs(Object[] args) {
		accumulatedMessages = new ArrayDeque<ACLMessage>();
		SecondName = (String)args[0];
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

	public List<CargoInfo> FindNewRoute(DeliveryInfoForPath deliveryInfoForPath) {
		// TODO Auto-generated method stub
		// Найти маршрут только для одного груза, значит был передан только один груз и
		// в словаре не будет ключей с именем "ci"
		// Есть уже готовый маршрут и нужно попробовать добавить груз в него
		// В первом случае начальной точкой будет координата 0:0 и время 8:30
		// Во втором случае начальной точкой будет конечные данные маршрута, значит
		// нужная функция для получения этих начальных данных
		// Также нужна функция для высчитывания расстояния, времени на дорогу
		// Для высчитывания времени нужно знать скорость грузовика, значит надо её как-то передать
		// Для высчитывания расстояния нужно знать начальную и конечную точку
		
		return null;
	}
	
	private int CountRoadTime(Double speed, double roadLength) {
		int time = (int) Math.ceil(roadLength/speed);
		return time;
	}
	
	private double CountRoadLength(Point start, Point end) {
		double length = Math.sqrt( Math.pow(start.x-end.x,2) + Math.pow(start.y-end.y, 2) );
		return length;
	}
	
	public int ConvertTimeToMinuts(int hours, int minut) {
		return hours*60 + minut;
	}
	
	public boolean cc(RouteInfo ri, double speed, Point end, int mintime, int maxtime, CargoInfo cargoInfo) {
		int time = ri.getLastTimeValue() + CountRoadTime(speed, CountRoadLength(ri.getLastPoint(), end));
		int delay = check(time, mintime, maxtime);
		if(delay >= 0) {
			ri.add(cargoInfo, delay, end, time);
			return true;
		} else {
			return false;
		}
	}

	private int check(int time, int mintime, int maxtime) {
		// TODO Auto-generated method stub
		if(time < mintime)
			return mintime - time;
		if(time >= mintime && time <= maxtime)
			return 0;
		else
			return -1;
	}
}
