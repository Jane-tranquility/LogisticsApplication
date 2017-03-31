package factory;

import entity.NetworkPair;
import entity.NetworkPairImpl;

public class NetworkPairFactory {
	public static NetworkPair loadNetWorkPair(String facilityName, String neighborName, int distance, int speed, int hourPerDay) {
		return new NetworkPairImpl(facilityName, neighborName, distance, speed, hourPerDay);
	}
}
