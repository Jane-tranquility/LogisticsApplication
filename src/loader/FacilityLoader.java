package loader;

import java.util.Map;

import entity.Facility;

public interface FacilityLoader {
	public Map<String, Facility> loadFacilities(String fileName);
}
