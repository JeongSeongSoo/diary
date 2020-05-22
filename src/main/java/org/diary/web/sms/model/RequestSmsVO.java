package org.diary.web.sms.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(propOrder = {"service", "send"})
@XmlRootElement(name = "request")
public class RequestSmsVO {

	private ServiceVO service;
	private SendVO send;
	
}
