package dto;

public class LinkPairDTO implements Comparable<LinkPairDTO>{
	public String neighborName;
	public double days;
	public LinkPairDTO(String neighborName, double days) {
		//super();
		this.neighborName = neighborName;
		this.days = days;
	}
	@Override
	public int compareTo(LinkPairDTO o) {
		 if (o.neighborName.charAt(0) != neighborName.charAt(0))
	            return this.neighborName.charAt(0) - o.neighborName.charAt(0) ;
	        else
	            return this.neighborName.charAt(1) - o.neighborName.charAt(1);
	        
	}
	
}
