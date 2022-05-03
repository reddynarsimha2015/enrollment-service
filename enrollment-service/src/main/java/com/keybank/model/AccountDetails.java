/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.model;

import lombok.Data;

/**
 * @author YNBR 05-Apr-2022
 *
 */
@Data
public class AccountDetails {
private String cardNumber;
private String cvv;
private String cardOnName;
private String expDate;
}
