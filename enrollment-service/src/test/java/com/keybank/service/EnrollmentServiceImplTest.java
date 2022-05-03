/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.keybank.builder.EnrollmentRequestBuilder;
import com.keybank.builder.EnrollmentResponseBuilder;
import com.keybank.dao.EnrollmentDao;
import com.keybank.dao.EnrollmentDaoImpl;
import com.keybank.exception.BusinessException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.AccountDetails;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.svcClient.O2ServiceClient;
import com.keybank.svcClient.O2ServiceClientImpl;

/**
 * @author YNBR 08-Apr-2022
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EnrollmentService.class,EnrollmentServiceImpl.class,
	O2ServiceClient.class,EnrollmentDao.class,EnrollmentRequestBuilder.class,EnrollmentResponseBuilder.class})
class EnrollmentServiceImplTest {
	@Mock
	O2ServiceClientImpl mockO2ServiceClient;
	@Mock
	EnrollmentDaoImpl mockEnrollmentDao;
	
	@Mock
	EnrollmentRequestBuilder mockEnrollmentRequestBuilder;
	
	@Mock
	EnrollmentResponseBuilder mockEnrollmentResponseBuilder;
	@InjectMocks//create the object and inject mock object to controller
	EnrollmentServiceImpl mockService;

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

	//succcess scenerio
	@Test
	void testEnollment_SuccessScenarios() throws Exception {
		PowerMockito.when(mockEnrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
		.thenCallRealMethod();
		
		PowerMockito.when(mockO2ServiceClient.enrollemnt(ArgumentMatchers.any(O2ServiceRequest.class)))
		.thenReturn(buildO2Response());
		
		
		PowerMockito.when(mockEnrollmentRequestBuilder.buildEnrollDaoRquest(ArgumentMatchers.any(EnrollmentRequest.class),ArgumentMatchers.anyString()))
		.thenCallRealMethod();
		
		PowerMockito.when(mockEnrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
		.thenReturn(builddaoResponse());
		
		PowerMockito.when(mockEnrollmentResponseBuilder.buildEnrollmentResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
		.thenCallRealMethod();
		
		EnrollmentRequest req=new EnrollmentRequest();
	    req.setMobileNum("121212");
	    req.setBillDate("22-04-2023");
	    req.setAmount(2000.0f);
	    req.setCustomerName("sreenu");
	    req.setPaymentDate("23-04-2039");
	    req.setProviderType("o2");
	    AccountDetails acc=new AccountDetails();
	    acc.setCardNumber("12121");
	    acc.setCardOnName("stee");
	    acc.setCvv("121");
	    acc.setExpDate("12-2020");
	    req.setAccountdetails(acc);
	    EnrollmentResponse resp = mockService.enollment(req, "web");
	    assertNotNull(resp);
	    assertEquals("0", resp.getRespCode());
		
		
	}
	// Business Exception
	@Test
	void testEnollment_BusinessExceptionScenarios()  {
		EnrollmentResponse resp;
		try {
			PowerMockito.when(mockEnrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
			.thenCallRealMethod();
			
			PowerMockito.when(mockO2ServiceClient.enrollemnt(ArgumentMatchers.any(O2ServiceRequest.class)))
			.thenReturn(buildO2Response());
			
			
			PowerMockito.when(mockEnrollmentRequestBuilder.buildEnrollDaoRquest(ArgumentMatchers.any(EnrollmentRequest.class),ArgumentMatchers.anyString()))
			.thenCallRealMethod();
			
			PowerMockito.when(mockEnrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
			.thenThrow(new BusinessException("100","Invalid MobileNumber"));
			
			PowerMockito.when(mockEnrollmentResponseBuilder.buildEnrollmentResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
			.thenCallRealMethod();
			
			EnrollmentRequest req=new EnrollmentRequest();
			req.setMobileNum("121212");
			req.setBillDate("22-04-2023");
			req.setAmount(2000.0f);
			req.setCustomerName("sreenu");
			req.setPaymentDate("23-04-2039");
			req.setProviderType("o2");
			AccountDetails acc=new AccountDetails();
			acc.setCardNumber("12121");
			acc.setCardOnName("stee");
			acc.setCvv("121");
			acc.setExpDate("12-2020");
			req.setAccountdetails(acc);
			resp = mockService.enollment(req, "web");
		} catch (O2ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			// assertNotNull(resp);
			    assertEquals("100", e.getRespCode());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
		
	}
//system Exception
	@Test
	void testEnollment_SystemExceptionScenarios()  {
		EnrollmentResponse resp;
		try {
			PowerMockito.when(mockEnrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
			.thenCallRealMethod();
			
			PowerMockito.when(mockO2ServiceClient.enrollemnt(ArgumentMatchers.any(O2ServiceRequest.class)))
			.thenReturn(buildO2Response());
			
			
			PowerMockito.when(mockEnrollmentRequestBuilder.buildEnrollDaoRquest(ArgumentMatchers.any(EnrollmentRequest.class),ArgumentMatchers.anyString()))
			.thenCallRealMethod();
			
			PowerMockito.when(mockEnrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
			.thenThrow(new SystemException("105","Sql grammer exception"));
			
			PowerMockito.when(mockEnrollmentResponseBuilder.buildEnrollmentResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
			.thenCallRealMethod();
			
			EnrollmentRequest req=new EnrollmentRequest();
			req.setMobileNum("121212");
			req.setBillDate("22-04-2023");
			req.setAmount(2000.0f);
			req.setCustomerName("sreenu");
			req.setPaymentDate("23-04-2039");
			req.setProviderType("o2");
			AccountDetails acc=new AccountDetails();
			acc.setCardNumber("12121");
			acc.setCardOnName("stee");
			acc.setCvv("121");
			acc.setExpDate("12-2020");
			req.setAccountdetails(acc);
			resp = mockService.enollment(req, "web");
		} catch (O2ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			// assertNotNull(resp);
			   e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			assertEquals("105", e.getRespCode());
		}
	   
		
		
	}
	
	
	/**
	 * @return
	 */
	private EnrollmentDaoResponse builddaoResponse() {
		EnrollmentDaoResponse resp=new EnrollmentDaoResponse();
		resp.setRespCode("0");
		resp.setRespMsg("Success");
		return resp;
	}

	/**
	 * @return
	 */
	private O2ServiceResponse buildO2Response() {
		// TODO Auto-generated method stub
		O2ServiceResponse o2Resp=new O2ServiceResponse();
		o2Resp.setRespCode("0");
		o2Resp.setRespMsg("Success");
		return o2Resp;
	}

}
