/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.svcClient;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.service.EnrollmentServiceImpl;
import com.keybank.util.EnrollmentConstant;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@Component
public class O2ServiceClientImpl implements O2ServiceClient{
	@Autowired
	RestTemplate restTemplate;

	private static final org.slf4j.Logger Logger=org.slf4j.LoggerFactory.getLogger(O2ServiceClientImpl.class);
	
	@Override
	public O2ServiceResponse enrollemnt(O2ServiceRequest request) throws O2ServiceException {
		Logger.debug("Enter into o2  Service -O2ServiceClientImpl API");
		Logger.info("o2ServiceRequest object is :"+request);
		// TODO Auto-generated method stub
		//get the request from service
		//prepare the request for o2 service like add headers,body
		//create RestTemplate and make call to O2Service ,get the response
		String uri="http://localhost:8686/v1/mobile/verify";
		//RestTemplate restTemplate=new RestTemplate();
		restTemplate.getMessageConverters()
		.add(new MappingJackson2HttpMessageConverter());
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set(EnrollmentConstant.CLIENT_ID, EnrollmentConstant.HDFC);
		header.set(EnrollmentConstant.MSGTS, new Date().toString());
		header.set(EnrollmentConstant.REQUESTID, (String) UUID.randomUUID().toString().subSequence(0, 15));
		header.set(EnrollmentConstant.VERSION, EnrollmentConstant.versionNo);
		HttpEntity<O2ServiceRequest> entity = new HttpEntity<O2ServiceRequest>(request, header);
			

	      ResponseEntity<O2ServiceResponse>  response= restTemplate.exchange(uri, HttpMethod.POST, entity, O2ServiceResponse.class);
	      O2ServiceResponse o2ServiceResponse=null;
	      try {
	      if(response.getStatusCode().is2xxSuccessful()) {
	    	  o2ServiceResponse= response.getBody();
	      }else if(response.getStatusCode().is4xxClientError()) {
	    	  throw new O2ServiceException("400","client Error");
	      }
	      else if(response.getStatusCode().is5xxServerError()) {
	    	  throw new O2ServiceException("500","Server rror");
	      }
	      }
	      catch(O2ServiceException os) {
	    	  Logger.error("enter into O2ServiceException: "+os);
	    	  throw os;
	    	  
	      }
	      catch(RestClientException os) {
	    	  Logger.error("enter into RestClientException: "+os);
	    	  throw os;
	    	  
	      }
	      Logger.debug("Exit into o2  Service -O2ServiceClientImpl API");
			Logger.info("o2ServiceRequest object is :"+o2ServiceResponse);
	      
		return o2ServiceResponse;
	}
	/*
	 * public static void main(String[] args) throws O2ServiceException {
	 * O2ServiceClientImpl service=new O2ServiceClientImpl(); O2ServiceRequest
	 * req=new O2ServiceRequest(); req.setMobileNum("999999999");
	 * service.enrollemnt(req); }
	 */
}
