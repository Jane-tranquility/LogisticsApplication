package loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.FacilityNetworkDTO;
import entity.NetworkPair;
import factory.NetworkPairFactory;

public class NetWorkLoaderImpl implements NetworkLoader{

	@Override
	public Map<String, List<NetworkPair>> loadNetworkMap(List<List<FacilityNetworkDTO>> fndtos) {
		Map<String, List<NetworkPair>> map = new HashMap<String, List<NetworkPair>>();
		for (List<FacilityNetworkDTO> l: fndtos) {
			String facilityNameRequest = l.get(0).facilityName;
			List<NetworkPair> list = new ArrayList<NetworkPair>();
			for (FacilityNetworkDTO fndto : l) {
				list.add(NetworkPairFactory.loadNetWorkPair( fndto.facilityName, fndto.facilityNeighbourName, fndto.distance, fndto.speed, fndto.hourPerDay));
			}
			map.put(facilityNameRequest, list);
		}
		return map;
	}
	

}
