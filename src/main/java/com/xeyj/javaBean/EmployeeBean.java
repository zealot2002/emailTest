package com.xeyj.javaBean;

public class EmployeeBean {

	public boolean valid(String account, String password) {  
        boolean isValid = false;  
        DBAccess db = new DBAccess();  
 
        if(db.createConn()) {  
            String sql = "select * from employee where account='"+account+"' and password='"+password+"'";  
            db.query(sql); 
            if(db.next()) {
                isValid = true;
            }
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
        }  
        return isValid;  
    } 
	
	public boolean isExist(String account) {  
	    boolean isExist = false;  
	    DBAccess db = new DBAccess();  
	    if(db.createConn()) {  
	        String sql = "select * from user where account='"+account+"'";  
	        db.query(sql);  
	        if(db.next()) {  
	            isExist = true;  
	        }  
	        db.closeRs();  
	        db.closeStm();  
	        db.closeConn();  
	    }  
	    return isExist;  
	} 
	
	public void add(String account, String password, String email) {  
	    DBAccess db = new DBAccess();  
	    if(db.createConn()) {  
	        String sql = "insert into user(account,password,email) values('"+account+"','"+password+"','"+email+"')";  
	        db.update(sql);  
	        db.closeStm();  
	        db.closeConn();  
	    }  
	} 
}
