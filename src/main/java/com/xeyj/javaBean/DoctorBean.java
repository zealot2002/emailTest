package com.xeyj.javaBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.xeyj.pojo.DoctorBO;
import com.xeyj.util.DateAndTimeUtil;
import com.xeyj.util.DateUtil;

public class DoctorBean extends BaseBean{
	private static final String doctorStateArr[] = {"正常","未完善信息","未审核","已拒绝"};
	private static final String[] sexArr = {"女","男"};
	private static final String[] jobTitleArr = {"无","主任医师","副主任医师","主治医师","住院医师"};
	private static final String[] dutyArr = {"无","院长","副院长", "主任", "副主任"};
	
	
	public List<DoctorBO> getList() throws Exception {  
        DBAccess db = new DBAccess();  
 
        if(db.createConn()) {  
            String sql = "select * from t_doctor  order by signup_time desc";  
            ResultSet rs = db.queryAll(sql); 
            List<DoctorBO> list = resultsetToVO(rs);
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return list;  
        }  
        return null;
    } 
	
	private List resultsetToVO(ResultSet rs)
		      throws Exception{
		    List<DoctorBO> list=new ArrayList();
		    while(rs.next()){
		    	DoctorBO bo = new DoctorBO();
		    	bo.setId(rs.getInt("id")+"");
		    	bo.setName(rs.getString("user_name"));
		    	int sex = rs.getInt("sex");
		    	
		    	if(sex>1)
		    		sex = 1;
		    	bo.setSex(sexArr[sex]);
		    	bo.setHospital(new HospitalBean().getName(rs.getInt("hospital_has_department_hospital_id")+""));
		    	bo.setDepartment(new DepartmentBean().getName(rs.getInt("hospital_has_department_department_id")+""));
		    	int i = rs.getInt("job_title");
		    	if(i<5&&i>=0){
		    		bo.setJobTitle(jobTitleArr[i]);
		    	}else{
		    		bo.setJobTitle(jobTitleArr[0]);
		    	}
//		    	int j = rs.getInt("social_title");
//		    	if(j<5&&j>=0){
//		    		bo.setJobDuty(dutyArr[j]);
//		    	}else{
//		    		bo.setJobDuty(dutyArr[0]);
//		    	}
		    	bo.setExpertise(rs.getString("expertise"));
		    	bo.setPhone(rs.getString("login_name"));//should be mobile
		    	bo.setState(doctorStateArr[rs.getInt("status")]);
		    	bo.setBarCode(rs.getString("barcode"));
		    	java.sql.Timestamp time = rs.getTimestamp("signup_time");
		    	bo.setRegisterTime(DateAndTimeUtil.timestampToString(time));
		      list.add(bo);
		    }
		    return list;
	  }
	
	public String getName(String id) throws Exception {  
        DBAccess db = new DBAccess();  
        String name = null;
        if(db.createConn()) {  
            String sql = "select name from t_doctor where id = "+id;  
            ResultSet rs = db.queryAll(sql); 
            while(rs.next()){
            	name = (rs.getString("name"));
            	break;
            }
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
        }  
        return name;
    }
	
	public DoctorBO getObjById(String id){
		DBAccess db = new DBAccess();  
		DoctorBO bo = new DoctorBO();
		try {
			 if(db.createConn()) {  
		            String sql = "select * from t_doctor where id = "+id;  
		            ResultSet rs = db.queryAll(sql); 
		            while(rs.next()){
		            	bo.setId(rs.getInt("id")+"");
				    	bo.setName(rs.getString("user_name"));
				    	bo.setSex(sexArr[rs.getInt("sex")]);
				    	bo.setHospital(new HospitalBean().getName(rs.getInt("hospital_has_department_hospital_id")+""));
				    	bo.setDepartment(new DepartmentBean().getName(rs.getInt("hospital_has_department_department_id")+""));
				    	int i = rs.getInt("job_title");
				    	if(i<5&&i>=0){
				    		bo.setJobTitle(jobTitleArr[i]);
				    	}else{
				    		bo.setJobTitle(jobTitleArr[0]);
				    	}
				    	int j = rs.getInt("social_title");
				    	if(j<5&&j>=0){
				    		bo.setJobDuty(dutyArr[j]);
				    	}else{
				    		bo.setJobDuty(dutyArr[0]);
				    	}
				    	bo.setExpertise(rs.getString("expertise"));
				    	bo.setPhone(rs.getString("login_name"));//should be mobile
				    	bo.setState(doctorStateArr[rs.getInt("status")]);
				    	bo.setBarCode(rs.getString("barcode"));
				    	
				    	java.sql.Timestamp time = rs.getTimestamp("signup_time");
				    	bo.setRegisterTime(DateAndTimeUtil.timestampToString(time));
				    	
				    	break;
		            }
		            db.closeRs();  
		            db.closeStm();  
		            db.closeConn();  
		        }  
		} catch (Exception e) {
			
		}
       return bo;
	}
	public List<DoctorBO> getyesterdayList() throws Exception {  
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select * from t_doctor where signup_time >= '"+yesterday+" 00:00:00' and signup_time < '"+yesterday+" 23:59:59'";
            System.out.println("getyesterdayList sql = "+sql);
            ResultSet rs = db.queryAll(sql); 
            List<DoctorBO> list = resultsetToVO(rs);
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return list;  
        }  
        return null;
    } 
	
	public int getCount() throws Exception {  
        return super.getCount("t_doctor");
    }

	public int getDailyCount() throws Exception{
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select count(*) from t_doctor where signup_time >= '"+yesterday+" 00:00:00' and signup_time < '"+yesterday+" 23:59:59'";
            int count = db.queryCount(sql); 
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return count;
        }  
        return 0;
	}
	
	public int getDailyInvalidCount() throws Exception{
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select * from t_doctor where status!=0 and signup_time >= '"+yesterday+" 00:00:00' and signup_time < '"+yesterday+" 23:59:59'";
            ResultSet rs = db.queryAll(sql); 
            int i=0;
            while(rs.next()){
		    	i++;
		    }
            
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return i;
        }  
        return 0;
	}

	public int getInvalidCount() throws SQLException {
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select count(*) from t_doctor where status!=0";
            int count = db.queryCount(sql); 
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return count;
        }  
        return 0;
	}
}
