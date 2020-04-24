package supply.delivery;

public interface IVehicle {
	double GetWeight();
	double GetFreeWeight();
	double GetSpeed();
	void SetType(Object arg);
	void PutCargo(Double cargoWeight);
	void refresh();
}
