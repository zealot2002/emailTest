package com.xeyj.javaBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xeyj.pojo.AdditionalRegisterBO;
import com.xeyj.pojo.DoctorBO;
import com.xeyj.pojo.PatientBO;
import com.xeyj.util.DateAndTimeUtil;
import com.xeyj.util.DateUtil;


public class AdditionalRegisterBean extends BaseBean{
	final String[] arrayState = {"�ύ����","ȷ������","����ִ��","����"};
	
	public List<AdditionalRegisterBO> getList(){
        DBAccess db = new DBAccess();  
		 try {
			 if(db.createConn()) {  
		         String sql = "select * from t_additional_register order by create_time desc";  
		         ResultSet rs = db.queryAll(sql); 
		         List<AdditionalRegisterBO> list = resultsetToVO(rs);
		         db.closeRs();  
		         db.closeStm();  
		         db.closeConn();  
		         return list;  
		     }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<AdditionalRegisterBO>();
    } 
	
	private List<AdditionalRegisterBO> resultsetToVO(ResultSet rs)
		      throws Exception{
	    List<AdditionalRegisterBO> list=new ArrayList<AdditionalRegisterBO>();
	    while(rs.next()){
	    	AdditionalRegisterBO bo = new AdditionalRegisterBO();
	    	bo.setId(rs.getInt("id")+"");
        	
        	DoctorBO doctorBO = new DoctorBean().getObjById(rs.getInt("doctor_id")+"");
	    	bo.setDoctor(doctorBO.getName());
	    	bo.setDoctorPhone(doctorBO.getPhone());
	    	bo.setDoctorHospital(doctorBO.getHospital());
	    	bo.setDoctorDepartment(doctorBO.getDepartment());
	    	bo.setDoctorJobTitle(doctorBO.getJobTitle());
	    	
	    	PatientBO patientBO = new PatientBean().getObjById(rs.getInt("patient_id")+"");
	    	 
	    	bo.setPatient(patientBO.getName());
	    	bo.setPatientPhone(patientBO.getPhone());
	    	bo.setState(arrayState[rs.getInt("status")]);
	    	
	    	java.sql.Timestamp time = rs.getTimestamp("create_time");
	    	bo.setCreateTime(DateAndTimeUtil.timestampToString(time));
	    	
	    	bo.setMedicalRecordId(rs.getInt("medical_record_id")+"");
	        list.add(bo);
	    }
	    return list;
	}

	public AdditionalRegisterBO getObjById(String id){
		DBAccess db = new DBAccess();  
		AdditionalRegisterBO bo = new AdditionalRegisterBO();
		try {
			 if(db.createConn()) {  
		            String sql = "select * from t_additional_register where id = "+id;  
		            ResultSet rs = db.queryAll(sql); 
		            while(rs.next()){
		            	bo.setId(rs.getInt("id")+"");
		            	
		            	DoctorBO doctorBO = new DoctorBean().getObjById(rs.getInt("doctor_id")+"");
				    	bo.setDoctor(doctorBO.getName());
				    	bo.setDoctorPhone(doctorBO.getPhone());
				    	bo.setDoctorHospital(doctorBO.getHospital());
				    	bo.setDoctorDepartment(doctorBO.getDepartment());
				    	bo.setDoctorJobTitle(doctorBO.getJobTitle());
				    	
				    	PatientBO patientBO = new PatientBean().getObjById(rs.getInt("patient_id")+"");
				    	 
				    	bo.setPatient(patientBO.getName());
				    	bo.setPatientPhone(patientBO.getPhone());
				    	bo.setState(arrayState[rs.getInt("status")]);
				    	
				    	java.sql.Timestamp time = rs.getTimestamp("create_time");
				    	bo.setCreateTime(DateAndTimeUtil.timestampToString(time));
				    	bo.setMedicalRecordId(rs.getInt("medical_record_id")+"");
				    	break;
		            }

		            db.closeRs();  
		            db.closeStm();  
		            db.closeConn();  
		        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	public int getCount() throws Exception {
		return super.getCount("t_additional_register");
	}

	public int getDailyCount() throws Exception{
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select count(*) from t_additional_register where create_time >= '"+yesterday+" 00:00:00' and create_time < '"+yesterday+" 23:59:59'";
            int count = db.queryCount(sql); 
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return count;
        }  
        return 0;
	}
	
}
