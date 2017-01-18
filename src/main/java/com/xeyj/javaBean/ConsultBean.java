package com.xeyj.javaBean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xeyj.pojo.ConsultBO;
import com.xeyj.pojo.DoctorBO;
import com.xeyj.pojo.GreenRodeBO;
import com.xeyj.pojo.PatientBO;
import com.xeyj.util.DateAndTimeUtil;
import com.xeyj.util.DateUtil;


public class ConsultBean extends BaseBean{
//	final String[] arrayState = {"��ͨ��","�����","�Ѿܾ�"};
	
	public List<ConsultBO> getList(){
        DBAccess db = new DBAccess();  
		 try {
			 if(db.createConn()) {  
		         String sql = "select * from t_consultation_online order by create_time desc";  
		         ResultSet rs = db.queryAll(sql); 
		         List<ConsultBO> list = resultsetToVO(rs);
		         db.closeRs();  
		         db.closeStm();  
		         db.closeConn();  
		         return list;  
		     }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ConsultBO>();
    } 
	
	private List<ConsultBO> resultsetToVO(ResultSet rs)
		      throws Exception{
		    List<ConsultBO> list=new ArrayList<ConsultBO>();
		    while(rs.next()){
		    	ConsultBO bo = new ConsultBO();
		    	bo.setId(rs.getInt("id")+"");

		    	java.sql.Timestamp time = rs.getTimestamp("create_time");
		    	bo.setCreateTime(DateAndTimeUtil.timestampToString(time));
		    	
		        list.add(bo);
		    }
		    return list;
	}

	public ConsultBO getObjById(String id){
		DBAccess db = new DBAccess();  
		ConsultBO bo = new ConsultBO();
		try {
			 if(db.createConn()) {  
		            String sql = "select * from t_consultation_online where id = "+id;  
		            ResultSet rs = db.queryAll(sql); 
		            while(rs.next()){
		            	bo.setId(rs.getInt("id")+"");
				    	java.sql.Timestamp time = rs.getTimestamp("create_time");
				    	bo.setCreateTime(time.toString());
				    	
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
	
	public int getDailyCount() throws Exception{
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select count(*) from t_consultation_online where create_time >= '"+yesterday+" 00:00:00' and create_time < '"+yesterday+" 23:59:59'";
            int count = db.queryCount(sql); 
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return count;
        }  
        return 0;
	}

	public int getCount() throws Exception {
		return super.getCount("t_consultation_online");
	}
}
