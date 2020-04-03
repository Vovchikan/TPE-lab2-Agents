package supply.delivery;

public interface ICargoInfo {
	double GetWeight();
	String GetDestination();
	String GetId();
	void CreateInfoFromMessage(String message);
	void PrintInConsole();
}
