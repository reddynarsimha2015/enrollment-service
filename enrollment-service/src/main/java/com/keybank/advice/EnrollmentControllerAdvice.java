/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.keybank.exception.BusinessException;
import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentResponse;


/**
 * @author YNBR 14-Mar-2022
 *
 */
@RestControllerAdvice
public class EnrollmentControllerAdvice {
	@ExceptionHandler(value=EnrollmentRequestInvalidException.class)
	@ResponseBody
	public EnrollmentResponse handleException(EnrollmentRequestInvalidException e) {
		//prepare error resp to client
		EnrollmentResponse response=buildError(e.getRespCode(), e.getRespMsg());
		return response;
	}
	@ExceptionHandler(value=BusinessException.class)
	@ResponseBody
	public EnrollmentResponse handleException(BusinessException e) {
		//prepare error resp to client
		EnrollmentResponse response=buildError(e.getRespCode(), e.getRespMsg());
		return response;
	}
	@ExceptionHandler(value=SystemException.class)
	@ResponseBody
	public EnrollmentResponse handleException(SystemException e) {
		//prepare error resp to client
		EnrollmentResponse response=buildError(e.getRespCode(), e.getRespMsg());
		return response;
	}
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public EnrollmentResponse handleException(Exception e) {
		//prepare error resp to client
		EnrollmentResponse response=buildError("111", "");
		return response;
	}
	

	public EnrollmentResponse buildError(String responseCOde,String respMsg){
		EnrollmentResponse response=new EnrollmentResponse();
		response.setRespCode(responseCOde);
		response.setRespMsg(respMsg);
		return response;
	}
}
