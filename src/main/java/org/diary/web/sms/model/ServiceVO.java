package org.diary.web.sms.model;

import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(propOrder = { "transactionId", "serviceId", "userId", "userPw" })
public class ServiceVO {
	
	private String transactionId;
	private String serviceId = "MMS01";
	private String userId;
	private String userPw;
	
}
