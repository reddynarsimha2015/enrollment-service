/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.svcClient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.keybank.builder.EnrollmentRequestBuilder;
import com.keybank.builder.EnrollmentResponseBuilder;
import com.keybank.dao.EnrollmentDao;
import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.service.EnrollmentService;
import com.keybank.service.EnrollmentServiceImpl;

/**
 * @author YNBR 08-Apr-2022
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({O2ServiceClient.class,O2ServiceClientImpl.class,
	RestTemplate.class})
class O2ServiceClientImplTest {
	
	
	@Mock
	RestTemplate restTemplate;
	@InjectMocks
	O2ServiceClientImpl o2ServiceClientImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.keybank.svcClient.O2ServiceClientImpl#enrollemnt(com.keybank.model.O2ServiceRequest)}.
	 * @throws O2ServiceException 
	 */
	@Test
	void testEnrollemnt() throws O2ServiceException {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), 
				ArgumentMatchers.any(HttpEntity.class),ArgumentMatchers.any(Class.class))).thenReturn(buildResponseEntity());
		O2ServiceRequest req=new O2ServiceRequest();
		req.setAmount(1000.00f);
		req.setBillDate("22-04-2029");
		req.setClientId("web");
		req.setCorrationId("as23232");
		req.setCustomerName("sreenu");
		req.setMgTs("fsdsd");
		req.setMobileNum("083434343");
		req.setPaymentDate("22-04-2029");
		req.setProviderType("o2");
		O2ServiceResponse resp = o2ServiceClientImpl.enrollemnt(req);
		
	}

	@Test
	void testClientErrorEnrollemnt(){
	
			try {
				Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), 
						ArgumentMatchers.any(HttpEntity.class),ArgumentMatchers.any(Class.class))).thenReturn(buildClintResponseEntity());
				O2ServiceRequest req=new O2ServiceRequest();
				req.setAmount(1000.00f);
				req.setBillDate("22-04-2029");
				req.setClientId("web");
				req.setCorrationId("as23232");
				req.setCustomerName("sreenu");
				req.setMgTs("fsdsd");
				req.setMobileNum("083434343");
				req.setPaymentDate("22-04-2029");
				req.setProviderType("o2");
				O2ServiceResponse resp = o2ServiceClientImpl.enrollemnt(req);
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (O2ServiceException e) {
				e.printStackTrace();
				assertEquals("400",e.getRespCode());
				// TODO Auto-generated catch block
			
			}
		
		
	}
	
	@Test
	void testServerErrorEnrollemnt(){
	
			try {
				Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), 
						ArgumentMatchers.any(HttpEntity.class),ArgumentMatchers.any(Class.class))).thenReturn(buildServerResponseEntity());
				O2ServiceRequest req=new O2ServiceRequest();
				req.setAmount(1000.00f);
				req.setBillDate("22-04-2029");
				req.setClientId("web");
				req.setCorrationId("as23232");
				req.setCustomerName("sreenu");
				req.setMgTs("fsdsd");
				req.setMobileNum("083434343");
				req.setPaymentDate("22-04-2029");
				req.setProviderType("o2");
				O2ServiceResponse resp = o2ServiceClientImpl.enrollemnt(req);
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (O2ServiceException e) {
				e.printStackTrace();
				assertEquals("500",e.getRespCode());
				// TODO Auto-generated catch block
			
			}
		
		
	}
	
	
	
	/**
	 * @return
	 */
	private ResponseEntity<O2ServiceResponse> buildServerResponseEntity() {
		// TODO Auto-generated method stub
		O2ServiceResponse resp=new O2ServiceResponse();
		resp.setRespCode("500");
		resp.setRespMsg("client error");
		resp.setValid(true);
		ResponseEntity<O2ServiceResponse> responseEntity=new ResponseEntity<O2ServiceResponse>(resp,HttpStatus.BAD_GATEWAY);
		return responseEntity;
	}

	private ResponseEntity<O2ServiceResponse> buildResponseEntity() {
		// TODO Auto-generated method stub
		O2ServiceResponse resp=new O2ServiceResponse();
		resp.setRespCode("0");
		resp.setRespMsg("success");
		resp.setValid(true);
		ResponseEntity<O2ServiceResponse> responseEntity=new ResponseEntity<O2ServiceResponse>(resp,HttpStatus.OK);
		return responseEntity;
	}
	
	private ResponseEntity<O2ServiceResponse> buildClintResponseEntity() {
		// TODO Auto-generated method stub
		O2ServiceResponse resp=new O2ServiceResponse();
		resp.setRespCode("400");
		resp.setRespMsg("client error");
		resp.setValid(true);
		ResponseEntity<O2ServiceResponse> responseEntity=new ResponseEntity<O2ServiceResponse>(resp,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	


}
