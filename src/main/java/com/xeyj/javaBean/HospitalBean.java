package com.xeyj.javaBean;

import java.sql.ResultSet;


public class HospitalBean {
	
	public String getName(String id) throws Exception {  
        DBAccess db = new DBAccess();  
        String name = null;
        if(db.createConn()) {  
            String sql = "select name from t_hospital where id = "+id;  
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
	
}
