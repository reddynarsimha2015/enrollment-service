/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.keybank.exception.BusinessException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.service.EnrollmentServiceImpl;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@Component
public class EnrollmentDaoImpl extends StoredProcedure implements EnrollmentDao {
	
	org.slf4j.Logger Logger=org.slf4j.LoggerFactory.getLogger(EnrollmentDaoImpl.class);

	
	@Autowired
	public EnrollmentDaoImpl(JdbcTemplate jdbcTemplate) {
		//register storage name ,input,out params
		super(jdbcTemplate,"AUTO_BILL_PAYMTS_01");
		registerParams();
	}

	/**
	 * 
	 */
	private void registerParams() {
		//register in params
		declareParameter(new SqlParameter("CLIENT_ID_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("CARD_NUM_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("CVV_NUM_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("NAME_ON_CARD_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("EXPDATE_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("MOBILENUM_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("BILL_GEN_DATE_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("BILL_PAYMT_DATE_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("AMOUNT_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("PROVIDER_IN",Types.VARCHAR));
		//register out params
		declareParameter(new SqlOutParameter("RESP_CODE_OUT",Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESP_MESSAGE_OUT",Types.VARCHAR));
		declareParameter(new SqlOutParameter("ACK_NUM_OUT",Types.VARCHAR));
		
		compile();
	}

	@Override
	public EnrollmentDaoResponse enrollment(EnrollmentDaoRequest daorequest) throws BusinessException, SystemException{
		//System.out.println("---Enter into DAO Layer  Enrollment--------");
		Logger.debug("Enter into DAO -Enollment API");
		Logger.info("Enrollment object is :"+daorequest);
		// TODO Auto-generated method stub stub
		
		
		EnrollmentDaoResponse enrollmentDaoResponse=new EnrollmentDaoResponse();
		try {
			Map<String, Object> inParams=new HashMap<String, Object>();
			inParams.put("CLIENT_ID_IN", daorequest.getClientId());
			inParams.put("CARD_NUM_IN", daorequest.getClientId());
			inParams.put("CVV_NUM_IN", daorequest.getClientId());
			inParams.put("NAME_ON_CARD_IN", daorequest.getClientId());
			inParams.put("EXPDATE_IN", daorequest.getClientId());
			inParams.put("MOBILENUM_IN", daorequest.getClientId());
			inParams.put("BILL_GEN_DATE_IN", daorequest.getClientId());
			inParams.put("BILL_PAYMT_DATE_IN", daorequest.getClientId());
			inParams.put("AMOUNT_IN", daorequest.getClientId());
			inParams.put("PROVIDER_IN", daorequest.getClientId());
			Map<String, Object> outParams=super.execute(inParams);
			String dbRespcode=outParams.get("RESP_CODE_OUT").toString();
			String dbRespMsg=outParams.get("RESP_MESSAGE_OUT").toString();
			String  ackNo=null;
			if(outParams.get("RESP_CODE_OUT")!=null) {
				ackNo=outParams.get("RESP_CODE_OUT").toString();
			}
			System.out.println("--------------ackNo-------"+ackNo);
			System.out.println("--------------dbRespcode-------"+dbRespcode);
			System.out.println("--------------dbRespMsg-------"+dbRespMsg);
			 enrollmentDaoResponse=new EnrollmentDaoResponse();
				if("0".equals(dbRespcode)) {
					//TODO: convert database resp  to Dao Response
					enrollmentDaoResponse.setEnrollmentStatus("SUCCESS");
					enrollmentDaoResponse.setRespCode("0");
					enrollmentDaoResponse.setRespMsg("Erollment Done");
					
				}
				else if("100".equals(dbRespcode)||"101".equals(dbRespcode)||"102".equals(dbRespcode)) {
					throw new BusinessException(dbRespcode,dbRespMsg);
				}
				else {
					throw new SystemException(dbRespcode,dbRespMsg);
				}
		}
		catch(BusinessException e) {
			Logger.error("Enter into Business exception"+e);
			throw e;
		}
		catch(SystemException ee) {
			Logger.error("Enter into SystemException exception "+ee);
			throw ee;
		}
		Logger.debug("Exit into DAO -Enollment API");
		Logger.info("Enrollment Response object is :"+enrollmentDaoResponse);
		return enrollmentDaoResponse;
		
		
		

		
		
	}

}
