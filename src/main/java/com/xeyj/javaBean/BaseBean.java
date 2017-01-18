package com.xeyj.javaBean;

import java.sql.ResultSet;

public class BaseBean {
	
	public int getCount(String tableName) throws Exception {  
        DBAccess db = new DBAccess();  
        int count = 0;
        if(db.createConn()) {  
             String sql="select count(*) from "+tableName;
             ResultSet rs = db.queryAll(sql); 
             while(rs.next()){
            	 count = (rs.getInt(1));
         		 break;
             }
             db.closeRs();  
             db.closeStm();  
             db.closeConn();  
        }  
        return count;
    } 
	
}
