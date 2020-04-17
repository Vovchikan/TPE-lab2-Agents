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
		// ����� ������� ������ ��� ������ �����, ������ ��� ������� ������ ���� ���� �
		// � ������� �� ����� ������ � ������ "ci"
		// ���� ��� ������� ������� � ����� ����������� �������� ���� � ����
		// � ������ ������ ��������� ������ ����� ���������� 0:0 � ����� 8:30
		// �� ������ ������ ��������� ������ ����� �������� ������ ��������, ������
		// ������ ������� ��� ��������� ���� ��������� ������
		// ����� ����� ������� ��� ������������ ����������, ������� �� ������
		// ��� ������������ ������� ����� ����� �������� ���������, ������ ���� � ���-�� ��������
		// ��� ������������ ���������� ����� ����� ��������� � �������� �����
		if(deliveryInfoForPath.HaveRoute()) {
			// ���� ���� ������� �������, ����� �������� �� �������� ��������, ����� 
			// �������� ��������� ����� � ��������� ����������
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
