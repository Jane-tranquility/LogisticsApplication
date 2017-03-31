package dto;

import java.util.Map;

public class LogisticsRecordPairDTO {
	public String orderId;
	public Map<String, LogisticsRecord> lrMap;
	public LogisticsRecordPairDTO(String orderId, Map<String, LogisticsRecord> lrMap) {
		super();
		this.orderId = orderId;
		this.lrMap = lrMap;
	}
	
}
