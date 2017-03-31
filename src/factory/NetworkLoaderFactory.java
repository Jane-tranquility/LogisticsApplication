package factory;



import loader.NetWorkLoaderImpl;
import loader.NetworkLoader;

public class NetworkLoaderFactory {
	public static NetworkLoader loadNetworkLoader(int num) {
		if (num == 1)
			return new NetWorkLoaderImpl();
		//else if (num == 2)
			//return new NetWorkLoaderV2Impl();
			
		return null;
	} 
}
