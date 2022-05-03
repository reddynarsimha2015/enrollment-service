/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.model;

import lombok.Data;
import lombok.Setter;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@Data
public class O2ServiceResponse {
	private String respCode;
	private String respMsg;
	private boolean valid;
	private String decription;
}
