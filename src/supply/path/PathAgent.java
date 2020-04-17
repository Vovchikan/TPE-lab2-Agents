package supply.path;

import java.util.List;
import java.util.TreeMap;

import supply.agent.IAgentInfo;
import supply.agent.MyAgent;
import supply.cargo.CargoInfo;
import supply.delivery.DeliveryInfoForPath;

public class PathAgent extends MyAgent {
	private static int count = 1;
	private String SecondName;

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
		if(deliveryInfoForPath.HaveRoute()) {
			// Если есть готовый маршрут, нужно пройтись по готовому маршруту, чтобы 
			// получить начальное время и начальные координаты
			var params = GetStartParams(deliveryInfoForPath.GetRoute());
			int x = params.get("x");
			int y = params.get("y");
			int time = params.get("time");
			double speed = deliveryInfoForPath.GetSpeed();
			
		}
		return null;
	}
	
	private int CountRoadTime(Double speed, double roadLength) {
		int time = (int) Math.ceil(roadLength/speed);
		return time;
	}
	
	private double CountRoadLength(int x1, int y1, int x2, int y2) {
		double length = Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1-y2, 2) );
		return length;
	}
	
	public TreeMap<String, Integer> GetStartParams(List<CargoInfo> cargos){
		return null;
	}
	
	public int ConvertTimeToMinuts(int hours, int minut) {
		return hours*60 + minut;
	}
}
