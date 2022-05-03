/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.svcClient;

import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;

/**
 * @author YNBR 14-Mar-2022
 *
 */
public interface O2ServiceClient {
	public O2ServiceResponse enrollemnt(O2ServiceRequest request)throws O2ServiceException;

}
