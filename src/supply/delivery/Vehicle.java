package supply.delivery;

import java.io.Serializable;

public class Vehicle implements IVehicle, Serializable {

	private int weight;
	private double speed;
	private int freeWeight;

	@Override
	public void SetType(Object arg) {
		// TODO Auto-generated method stub
		if(arg.toString().equals("Small")) {
			weight = 100;
			speed = 89.25;
		}
		else if(arg.toString().equals("Big")) {
			weight = 200;
			speed = 64;
		}
		else if(arg.toString().equals("Middle")) {
			weight = 150;
			speed = 73.55;
		}
		else {
			// Do smth;
		}
		freeWeight = weight;
	}

	@Override
	public double GetWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public double GetSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public double GetFreeWeight() {
		// TODO Auto-generated method stub
		return freeWeight;
	}

	@Override
	public void PutCargo(Double cargoWeight) {
		freeWeight -= Math.ceil(cargoWeight);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		freeWeight = weight;
	}
}
