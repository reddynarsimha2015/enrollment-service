/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.exception;

/**
 * @author YNBR 18-Mar-2022
 *
 */
public class O2ServiceException extends Exception {
	public String respCode;
	public String respMsg;
	

	/**
	 * @param respCode
	 * @param respMsg
	 */
	public O2ServiceException(String respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}


	/**
	 * @return the respCode
	 */
	public String getRespCode() {
		return respCode;
	}


	/**
	 * @param respCode the respCode to set
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}


	/**
	 * @return the respMsg
	 */
	public String getRespMsg() {
		return respMsg;
	}


	/**
	 * @param respMsg the respMsg to set
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
}
