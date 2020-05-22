package org.diary.web.sms;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.diary.web.sms.model.InfoVO;
import org.diary.web.sms.model.RequestSmsVO;
import org.diary.web.sms.model.SendVO;
import org.diary.web.sms.model.ServiceVO;

public class SmsTest {
	
	public static String marshall(RequestSmsVO request) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(RequestSmsVO.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		marshaller.marshal(request, sr);
		System.out.println(sw.toString());
		return sw.toString();
	}
	
	public static void request() {// throws Exception {
		try {
			RequestSmsVO request = new RequestSmsVO();
			InfoVO info = new InfoVO();
			List<InfoVO> infoAry = new ArrayList<>();
			SendVO send = new SendVO();
			ServiceVO service = new ServiceVO();
			
			info.setMsgId("kra_user_msgId");
			info.setCallback("01099150616");
			info.setRecvPhn("01087399716");
			infoAry.add(info);
			
			send.setMsg("안녕하세요 테스트입니다.");
			send.setSubject("kra_user_subject");
			send.setInfo(infoAry);
			
			service.setTransactionId("321");
			service.setUserId("bcitest");
			service.setUserPw("1234");
			
			request.setService(service);
			request.setSend(send);
			
			List<NameValuePair> postParam = Arrays.asList(new BasicNameValuePair("format", "xml"), 
					new BasicNameValuePair("service", "MMS01"), 
					new BasicNameValuePair("req", marshall(request)));
			
			HttpPost post = new HttpPost("http://211.216.53.13:9080/BCUMSService/ums.bc");
			
			post.setEntity(new UrlEncodedFormEntity(postParam, "euc-kr"));
			
			SSLContextBuilder sslcontext = new SSLContextBuilder();
	        sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			
			try (CloseableHttpClient httpClient = HttpClients.custom()
															 .setSSLContext(sslcontext.build())
															 .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
															 .build(); 
					
					CloseableHttpResponse httpResponse = httpClient.execute(post)) {
				
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = httpResponse.getEntity();
					String content = EntityUtils.toString(entity);
					System.out.println(content);
					//ResponseSmsVO response = unmarsharll(content);
					//return response;
				}
			}
		} catch (Throwable e) {

		}
		
		/*
		List<NameValuePair> postParam = Arrays.asList(new BasicNameValuePair("format", "xml"), 
													  new BasicNameValuePair("service", "MMS01"), 
													  new BasicNameValuePair("req", XMLParse.marshall(request)));
		post.setEntity(new UrlEncodedFormEntity(postParam, "euc-kr"));
		
		SSLContextBuilder sslcontext = new SSLContextBuilder();
        sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		
		try (CloseableHttpClient httpClient = HttpClients.custom()
														 .setSSLContext(sslcontext.build())
														 .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
														 .build(); 
				
				CloseableHttpResponse httpResponse = httpClient.execute(post)) {
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				String content = EntityUtils.toString(entity);
				ResponseVO response = XMLParse.unmarsharll(content);
				return response;
			}
		}
		*/
	}
}
