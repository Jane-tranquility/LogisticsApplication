package loader;

import java.util.List;
import java.util.Map;

import dto.FacilityNetworkDTO;
import entity.NetworkPair;

public interface NetworkLoader {
	public Map<String, List<NetworkPair>> loadNetworkMap(List<List<FacilityNetworkDTO>> fndtos);
	
	
}
