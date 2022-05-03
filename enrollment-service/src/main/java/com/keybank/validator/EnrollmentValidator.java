/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.validator;

import org.springframework.stereotype.Component;

import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.model.EnrollmentRequest;

/**
 * @author YNBR 17-Mar-2022
 *
 */
@Component
public class EnrollmentValidator {
	public void validateRequest(EnrollmentRequest request) throws EnrollmentRequestInvalidException {
		//todo:validate the requesr ,if ir valid nothing return,else it will exception
		System.out.println("---------------Enter into validator----------");
		if(request==null) {
			throw new EnrollmentRequestInvalidException("err001", "No enrollmen Details");
		}
		if(request.getMobileNum()==null||"".equals(request.getMobileNum())) {
			throw new EnrollmentRequestInvalidException("err002", "MobileNumber Invalid Details");
		}
		if(request.getBillDate()==null||"".equals(request.getBillDate())) {
			throw new EnrollmentRequestInvalidException("err003", "BillDate Invalid Details");
		}
		if(request.getPaymentDate()==null||"".equals(request.getPaymentDate())) {
			throw new EnrollmentRequestInvalidException("err004", "PaymentDate Invalid Details");
		}
		if(request.getProviderType()==null||"".equals(request.getProviderType())) {
			throw new EnrollmentRequestInvalidException("err005", "ProviderType Invalid Details");
		}
	
		//todo :write valation for all the manditory elements like cardNumber,cvv,nameoncard,expdate
	}

}
