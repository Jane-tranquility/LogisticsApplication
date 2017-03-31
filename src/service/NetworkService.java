 package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dto.LowPathDTO;
import entity.NetworkPair;
import exception.ParameterException;
import factory.ItemLoaderFactory;
import factory.NetworkLoaderFactory;
import loader.ItemLoader;
import loader.NetworkLoader;

public class NetworkService {
    //private ArrayList<String> lowPath=new ArrayList<String>();
    //private static PathFinder pathFinder=PathFinderFactory.findPathFac(2);
    Map<String, List<NetworkPair>> networkMap = new HashMap<String, List<NetworkPair>>(); // valueObject == facilitynetworkdto
    private static NetworkLoader networkLoader = NetworkLoaderFactory.loadNetworkLoader(1);
    private volatile static NetworkService instance;
    private NetworkService(){
        //lowPath=pathFinder.findPath(start, end);
        networkMap = networkLoader.loadNetworkMap(FacilityService.getInstance().getFacilityNetwork());
    }
    
    public static NetworkService getInstance() {
        if (instance == null) {
            synchronized (NetworkService.class) {
                if (instance == null) {
                    instance = new NetworkService();
                }
            }
        }
        return instance;
    }
    
    List<String> lowPath=new ArrayList<String>();
    int lowest=Integer.MAX_VALUE;
    
    public LowPathDTO findBestPath(String start, String end) throws ParameterException{
    	if (!FacilityService.getInstance().containsFacility(start) || !FacilityService.getInstance().containsFacility(end)) 
    		throw new ParameterException("invalid facilityName");
    	int speed = networkMap.get(start).get(0).getSpeed();
    	int hourPerDay = networkMap.get(start).get(0).getHourPerDay();
    	
        List<String> path=new ArrayList<String>();
        path.add(start);
        findPath(start, end, path);
        
        int distance=0;
        for (int i=0; i< (lowPath.size()-1); i++){
            List<NetworkPair> neighbourFacilities=networkMap.get(lowPath.get(i));
            for (NetworkPair neighbourFacility: neighbourFacilities){
                if ((neighbourFacility.getNeighborName()).equals(lowPath.get(i+1))){
                    distance+=neighbourFacility.getDistance();
                    break;
                }
            }
        }
        List<String> lowPathLocal = new ArrayList<String>(); 
        for (String str : lowPath)
        	lowPathLocal.add(str);
        lowest = Integer.MAX_VALUE;
        lowPath = new ArrayList<String>();
        
        return new LowPathDTO(lowPathLocal.get(lowPathLocal.size()-1), lowPathLocal.get(0), lowPathLocal, distance, speed, hourPerDay);
        
    }


    public void findPath(String start, String end, List<String> path){

        
        if (!start.equals(end)){
           
            HashSet<NetworkPair> fromHere=new HashSet<NetworkPair>();
            List<NetworkPair> neighbourFacilities=networkMap.get(start);
            for (NetworkPair neighbourFacility: neighbourFacilities){
                fromHere.add(neighbourFacility);
            }
            
           
            for (NetworkPair neighbourFacility: fromHere){
                String neighbourName=neighbourFacility.getNeighborName();
                if (!path.contains(neighbourName)){
                    List<String> newPath=new ArrayList<String>();
                    for (String item: path){
                        newPath.add(item);
                    }
                    newPath.add(neighbourName);
                    findPath(neighbourName, end, newPath);
                    //path.remove(path.size()-1);
                }
            
            }
            return;
        }
        else {
            int sum=0;
            for (int i=0; i< (path.size()-1); i++){
                List<NetworkPair> neighbourFacilities=networkMap.get(path.get(i));
                for (NetworkPair neighbourFacility: neighbourFacilities){
                    if ((neighbourFacility.getNeighborName()).equals(path.get(i+1))){
                        sum+=neighbourFacility.getDistance();
                        break;
                    }
                }
            }
            if (sum<lowest){
                lowest=sum;
                lowPath=path;
            }
            return;
        }
       
    }
    
/*
    public LowPathDTO findShortestPathByFacilities(String facilityName1, String facilityName2) {
        
        return null;
        //PathFinderFactory.findPath(facilityName1, facilityName2, netWorkMap);
        // chicago, <chicago, newyork> 100
        // <chicago, washington> 200
        // <newyork, 
    }
   */
}


/*
//main
public static void main() {
    int shorestPath = NetworkService.getInstance().findShortestPathByFacilities(facility1, facility2);
}
*/