/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.builder;

import org.springframework.stereotype.Component;

import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;

/**
 * @author YNBR 05-Apr-2022
 *
 */
@Component
public class EnrollmentRequestBuilder {

/**
 * Desciption:This method is uesd to prepare O2ServiceRequest with help of EnrollmentRequest
 *@param req
 *@return o2ServiceRequest
 */
public O2ServiceRequest buildO2Request(EnrollmentRequest req) {
	O2ServiceRequest o2ServiceRequest=new O2ServiceRequest();
	o2ServiceRequest.setMobileNum(req.getMobileNum());
	o2ServiceRequest.setAmount(req.getAmount());
	o2ServiceRequest.setBillDate(req.getBillDate());
	o2ServiceRequest.setCustomerName(req.getCustomerName());
	o2ServiceRequest.setPaymentDate(req.getPaymentDate());
	o2ServiceRequest.setProviderType(req.getProviderType());
	return o2ServiceRequest;
}
/**
 * Description:This method is uesd to prepare EnrollmentDaoRequest with help of EnrollmentRequest
 *@param req
 * @param clientId 
 *@return daoRequest
 */ 
public  EnrollmentDaoRequest  buildEnrollDaoRquest(EnrollmentRequest req, String clientId) {
	EnrollmentDaoRequest daoRequest=new EnrollmentDaoRequest();
	daoRequest.setClientId(clientId);
	daoRequest.setMobileNum(req.getMobileNum());
	daoRequest.setAmount(req.getAmount());
	daoRequest.setBillDate(req.getBillDate());
	daoRequest.setCustomerName(req.getCustomerName());
	daoRequest.setPaymentDate(req.getPaymentDate());
	daoRequest.setProviderType(req.getProviderType());
	daoRequest.setCardNumber(req.getAccountdetails().getCardNumber());
	daoRequest.setCvv(req.getAccountdetails().getCvv());
	daoRequest.setCardOnName(req.getAccountdetails().getCardOnName());
	daoRequest.setExpDate(req.getAccountdetails().getExpDate());
	return daoRequest;
	
}
}
