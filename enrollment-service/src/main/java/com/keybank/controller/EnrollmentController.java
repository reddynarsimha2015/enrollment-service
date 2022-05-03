/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.controller;

import java.util.logging.Logger;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keybank.exception.BusinessException;
import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.service.EnrollmentService;
import com.keybank.validator.EnrollmentValidator;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@RequestMapping("/v1")
@RestController
//@WebService for soapwebservice
public class EnrollmentController {
 //@PostMapping("/enrollment")
	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	EnrollmentValidator validator;
	
	private static final org.slf4j.Logger Logger=org.slf4j.LoggerFactory.getLogger(EnrollmentController.class);
	
@RequestMapping(value="/enrollment", 
          method=RequestMethod.POST,
          produces="application/json",
          consumes="application/json")
	public EnrollmentResponse  enrollment(@RequestBody EnrollmentRequest enrollmentReqest,
			@RequestHeader(name="clientId",required =true) String clientId,
			@RequestHeader(name="requestId",required=true) String requestId,@RequestHeader(name="messageTs",required=true) String messageTs
			) throws EnrollmentRequestInvalidException, BusinessException, SystemException, O2ServiceException {
	MDC.put("ctlientId",clientId);
	MDC.put("messageTimeStamp",messageTs);
	Logger.debug("Enter into controller -Enollment API");
	Logger.info("Enrollment object is :"+enrollmentReqest);
	//1.get request data in json format from consumer/client
	//2.convert json request data into java using jackdon library with help of @RequestBody annotation
	//3.now got java object
	//validate request
	validator.validateRequest(enrollmentReqest);
	
	//5.call servicee layer  and get response
	EnrollmentResponse serviceResponse=enrollmentService.enollment(enrollmentReqest,clientId);
	
	//6 prepare final response in controller
	//7 send final response to consumer/client
	//8 while sending response to consumer,jackson will convert response  to json with help of
	//@ResponseBody
	//9 send response json  to consumer

	
	//19 get the response from service layer
	//20 verify the response, if need modify the  response
	//21 prepare final controller response
	//22 convert response oject to json
	//23  send response json to consumer/client
	
	//System.out.println("-------------Exit into controllerqqq-----------Enrollment---");
	
	Logger.debug("EXIT into controller -Enollment API");
	Logger.info("EnrollmentResponse object is :"+serviceResponse);

		return serviceResponse;
	}

}
