package factory;

import loader.FacilityLoader;
import loader.FacilityNullLoader;
import loader.FacilityXMLLoader;

public class FacilityLoaderFactory implements Factory{
	public static FacilityLoader loadFacilityLoader(String loadFormat) {
		if ("XML".equals(loadFormat))
			return new FacilityXMLLoader();
		else
			return new FacilityNullLoader();
	}
}
