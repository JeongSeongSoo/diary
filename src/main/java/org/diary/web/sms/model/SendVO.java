package org.diary.web.sms.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(propOrder = {"sendDttm", "msg", "subject", "info"})
public class SendVO {

	private String sendDttm = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN).format(new Date());
	private String msg;
	private List<InfoVO> info;
	private String subject;
	
}
