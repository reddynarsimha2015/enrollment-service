/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.model.EnrollmentRequest;

class EnrollmentValidatorTest {
	EnrollmentValidator validator=null;
	EnrollmentRequest enrollmentRequest=null;
	@BeforeEach
	public void setUp() throws Exception {
		validator=new EnrollmentValidator();
		enrollmentRequest=buildEnrollmentRequest();
	}
	

	
	/**
	 * @return
	 */
	private EnrollmentRequest buildEnrollmentRequest() {
		EnrollmentRequest enrollmentRequest=new EnrollmentRequest();
		enrollmentRequest.setAmount(100.0f);
		enrollmentRequest.setBillDate("24-01-2021");
		enrollmentRequest.setCustomerName("Sreenu");
		enrollmentRequest.setMobileNum("985557755775");
		enrollmentRequest.setPaymentDate("23-02-2022");
		enrollmentRequest.setProviderType("airtel");
		// TODO Auto-generated method stub
		return enrollmentRequest;
	}



	@AfterEach
public	void tearDown() throws Exception {
		validator=null;
	}

	
	@Test
	public void testValidate_EnrollmentRequest() {
		enrollmentRequest=null;
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err001", e.getRespCode());
		assertEquals("No enrollmen Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_Mobile_null_scenerio() {
		enrollmentRequest.setMobileNum(null);
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err002", e.getRespCode());
		assertEquals("MobileNumber Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_Mobile_Empty_scenerio() {
		enrollmentRequest.setMobileNum("");
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err002", e.getRespCode());
		assertEquals("MobileNumber Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_BillDate_Empty_scenerio() {
		enrollmentRequest.setBillDate("");
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err003", e.getRespCode());
		assertEquals("BillDate Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_BillDate_Null_scenerio() {
		enrollmentRequest.setBillDate(null);
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err003", e.getRespCode());
		assertEquals("BillDate Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_PaymentDate_Empty_scenerio() {
		enrollmentRequest.setPaymentDate("");
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err004", e.getRespCode());
		assertEquals("PaymentDate Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_PaymentDate_Null_scenerio() {
		enrollmentRequest.setPaymentDate(null);
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err004", e.getRespCode());
		assertEquals("PaymentDate Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_ProviderType_Empty_scenerio() {
		enrollmentRequest.setProviderType("");
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err005", e.getRespCode());
		assertEquals("ProviderType Invalid Details", e.getRespMsg());
		}
	}
	@Test
	public void testValidate_ProviderType_Null_scenerio() {
		enrollmentRequest.setProviderType(null);
		try {
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {
			// TODO Auto-generated catch block
		assertEquals("err005", e.getRespCode());
		assertEquals("ProviderType Invalid Details", e.getRespMsg());
		}
	}

}
