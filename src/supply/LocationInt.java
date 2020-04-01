package supply;

public class LocationInt implements ILocation{
	public int x;
	public int y;
	
	public LocationInt() {};
	
	public void SetLocation(Object params) {
		var coords = ((String)params).split("\\|",2);
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
	}
}
