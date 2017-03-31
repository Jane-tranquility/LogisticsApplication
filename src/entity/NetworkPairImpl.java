package entity;

public class NetworkPairImpl implements NetworkPair {
	private String facilityName;
	private String neighborName;
	private int distance;
	private int speed;
	private int hourPerDay;
	
	
	public NetworkPairImpl(String facilityName, String neighborName, int distance, int speed, int hourPerDay) {
		//super();
		this.facilityName = facilityName;
		this.neighborName = neighborName;
		this.distance = distance;
		this.speed = speed;
		this.hourPerDay = hourPerDay;
	}
	@Override
	public String getFacilityName() {
		// TODO Auto-generated method stub
		return facilityName;
	}
	@Override
	public String getNeighborName() {
		// TODO Auto-generated method stub
		return neighborName;
	}
	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return distance;
	}
	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}
	@Override
	public int getHourPerDay() {
		// TODO Auto-generated method stub
		return hourPerDay;
	}
	
	
	
}
