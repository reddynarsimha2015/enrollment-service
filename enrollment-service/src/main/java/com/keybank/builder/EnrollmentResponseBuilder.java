/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.builder;

import org.springframework.stereotype.Component;

import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentResponse;

/**
 * @author YNBR 05-Apr-2022
 *
 */
@Component
public class EnrollmentResponseBuilder {
	/**
	 * Desciption: this method is used to prepare the EnrollmentResponse with help EnrollmentDaoResponse
	 *@param daoResp
	 *@return daoResp
	 */
	public EnrollmentResponse buildEnrollmentResponse(EnrollmentDaoResponse daoResp) {
		EnrollmentResponse enrollmentResponse=new EnrollmentResponse();
	    enrollmentResponse.setEnrollmentStatus(daoResp.getEnrollmentStatus());
	    enrollmentResponse.setRespCode(daoResp.getRespCode());
	    enrollmentResponse.setRespMsg(daoResp.getRespMsg());
		return enrollmentResponse;
		
	}
}
