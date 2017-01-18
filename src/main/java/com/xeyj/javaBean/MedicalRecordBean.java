package com.xeyj.javaBean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xeyj.pojo.MedicalRecordBO;
import com.xeyj.util.DateAndTimeUtil;

public class MedicalRecordBean {
	
	public List getList(String patientId) {  
		System.out.println("MedicalRecordBean--> getList()  patientId = "+patientId);
		
        DBAccess db = new DBAccess();  
		try {
			 if(db.createConn()) {  
		            String sql = "select * from t_medical_record where patient_has_disease_patient_id = "+patientId+" order by update_time desc";  
		            ResultSet rs = db.queryAll(sql); 
		            List<MedicalRecordBO> list = resultsetToVO(rs);
		            db.closeRs();  
		            db.closeStm();  
		            db.closeConn();  
		            return list;  
		        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
       
        return new ArrayList();
    } 
	
	
	private List resultsetToVO(ResultSet rs)
		      throws Exception{
		    List<MedicalRecordBO> list=new ArrayList<MedicalRecordBO>();
		    
		    while(rs.next()){
		    	MedicalRecordBO bo = new MedicalRecordBO();
		    	bo.setId(rs.getInt("id")+"");
		    	
		    	bo.setDiseaseName(rs.getString("disease_name"));
		    	bo.setHospital(rs.getString("hospital_name"));
		    	bo.setDepartment(rs.getString("department_name"));
		    	
		    	java.sql.Timestamp time = rs.getTimestamp("visit_time");
		    	bo.setVisitTime(DateAndTimeUtil.timestampToString(time));
		    	
		        list.add(bo);
		    }
		    System.out.println("resultsetToVO()  list.size() = "+list.size());
		    return list;
	  }
	
	public MedicalRecordBO getObjById(String id){
		DBAccess db = new DBAccess();  
		MedicalRecordBO bo = new MedicalRecordBO();
		try {
			 if(db.createConn()) {
		            String sql = "select * from t_medical_record where id = "+id;  
		            ResultSet rs = db.queryAll(sql); 
		            while(rs.next()){
		            	bo.setId(rs.getString("id"));
		            	bo.setDiseaseName(rs.getString("disease_name"));
				    	bo.setHospital(rs.getString("hospital_name"));
				    	bo.setDepartment(rs.getString("department_name"));
				    	
				    	java.sql.Timestamp time = rs.getTimestamp("visit_time");
				    	bo.setVisitTime(DateAndTimeUtil.timestampToString(time));
				    	bo.setDeagnosticInfo(rs.getString("diagnostic_information"));
				    	bo.setDescription(rs.getString("disease_description"));
				    	bo.setMedication(rs.getString("medication"));
				    	getImgList(id,bo);
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
	
	private void getImgList(String medicalRecordId,MedicalRecordBO bo){
//		List<String> list = new ArrayList<String>();
//		DBAccess db = new DBAccess();  
//		try {
//			 if(db.createConn()) {  
//	            String sql = "select * from t_medical_image where medicalRecord_id = "+medicalRecordId;  
//	            ResultSet rs = db.queryAll(sql); 
//	            while(rs.next()){
//	            	String smallImgUri = OSSSignature.getSignature(rs.getString("thumbnail_url"));
//	            	bo.getSmallImgList().add(smallImgUri);
//	            	System.out.println("getImgList()  smallImgUri = "+smallImgUri);
//	            	bo.getBigImgList().add(rs.getString("url"));
//	            }
//
//	            db.closeRs();  
//	            db.closeStm();  
//	            db.closeConn();  
//	        }  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
//	public List<String> getImgUri(String id){
//		List<String> list = new ArrayList<String>();
//		list.add("http://www.edu-hb.com/Upload/NewsImg/201009/09/100909164330o2kmv.jpg");
//		list.add("http://pic.baike.soso.com/p/20131204/20131204135852-838486575.jpg");
//		list.add("http://t02.pic.sogou.com/e79a8706efd117f6-d20c2c70387e4933-0f564b1d85403309f41e1e58f6ad23fb_i.jpg");
//		return list;
//	}
}
