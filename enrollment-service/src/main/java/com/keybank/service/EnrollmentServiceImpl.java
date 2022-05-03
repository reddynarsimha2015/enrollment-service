/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keybank.builder.EnrollmentRequestBuilder;
import com.keybank.builder.EnrollmentResponseBuilder;
import com.keybank.controller.EnrollmentController;
import com.keybank.dao.EnrollmentDao;
import com.keybank.exception.BusinessException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.svcClient.O2ServiceClient;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@Component
public class EnrollmentServiceImpl implements EnrollmentService {
	@Autowired
	O2ServiceClient o2Serviceclient;
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	EnrollmentRequestBuilder enrollmentRequestBuilder;
	
	@Autowired
	EnrollmentResponseBuilder enrollmentResponseBuilder;
	
	private static final org.slf4j.Logger Logger=org.slf4j.LoggerFactory.getLogger(EnrollmentServiceImpl.class);
	@Override
	public EnrollmentResponse enollment(EnrollmentRequest req,String clientId) throws BusinessException, SystemException, O2ServiceException {
		Logger.debug("Enter into Service -Enollment API");
		Logger.info("Enrollment object is :"+req);
		EnrollmentDaoResponse enrollmentDaoResponse = null ;
		// TODO Auto-generated method stub
		//6. get request from controller
		//7prepare the o2ServiceRequest
		O2ServiceRequest o2ServiceRequest = enrollmentRequestBuilder.buildO2Request(req);
		//8 call 02 service  and get response
		O2ServiceResponse	o2ServiceResponse=o2Serviceclient.enrollemnt(o2ServiceRequest);
	
		if("0".equals(o2ServiceResponse.getRespCode())) {
			EnrollmentDaoRequest enrollmentDaoRequest = enrollmentRequestBuilder.buildEnrollDaoRquest(req,clientId);
			enrollmentDaoResponse= enrollmentDao.enrollment(enrollmentDaoRequest);
		}
		
		
		// prepare enrollment response
 
		EnrollmentResponse enrollmentResponse = enrollmentResponseBuilder.buildEnrollmentResponse(enrollmentDaoResponse);
		
		//16. perform some business logic likesorting,paging pagination, ---- etc
		//17  prepare the final service response
		//18. send service response to controller
		//System.out.println("------Exit into Service layer---Enrollment---");
		Logger.debug("EXIT into Service -Enollment API");
		Logger.info("Enrollment Response object is :"+enrollmentResponse);
		return enrollmentResponse;
	}

}
