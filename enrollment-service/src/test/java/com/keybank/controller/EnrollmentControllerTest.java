/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.keybank.model.AccountDetails;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.service.EnrollmentService;
import com.keybank.service.EnrollmentServiceImpl;
import com.keybank.validator.EnrollmentValidator;


/**
 * @author YNBR 07-Apr-2022
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EnrollmentController.class,EnrollmentValidator.class,EnrollmentService.class,EnrollmentServiceImpl.class})
class EnrollmentControllerTest {
	
	@InjectMocks//it will create controller object  and inject mock object into controller class
	EnrollmentController enrollmentController;
	
	@Mock
	EnrollmentService enrollmentService;
	
	@Mock
	EnrollmentValidator validator;


	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testEnrollment() throws Exception {
	PowerMockito.doCallRealMethod().when(validator).validateRequest(ArgumentMatchers.any(EnrollmentRequest.class));
	PowerMockito.when(enrollmentService.enollment(ArgumentMatchers.any(EnrollmentRequest.class), ArgumentMatchers.anyString()))
	.thenReturn(buildEnrollResp());
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
    EnrollmentResponse resp = enrollmentController.enrollment(req, "web", "1fsfs", "aadsas");
    assertNotNull(resp);
    assertEquals("0", resp.getRespCode());
	}


	/**
	 * @return
	 */
	private EnrollmentResponse buildEnrollResp() {
		EnrollmentResponse resp=new EnrollmentResponse();
		resp.setRespCode("0");
		resp.setRespMsg("success");
		resp.setEnrollmentStatus("delivered");
		// TODO Auto-generated method stub
		return resp;
	}

}
