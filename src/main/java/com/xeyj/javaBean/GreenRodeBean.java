package com.xeyj.javaBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xeyj.pojo.GreenRodeBO;
import com.xeyj.pojo.PatientBO;
import com.xeyj.util.DateAndTimeUtil;
import com.xeyj.util.DateUtil;


public class GreenRodeBean extends BaseBean{
	final String[] stateArr = {"�ύ����","ȷ������","����ִ��","����"};
	
	public List getList(){
        DBAccess db = new DBAccess();  
		 try {
			 if(db.createConn()) {  
		         String sql = "select * from t_express_lane_service order by create_time desc";  
		         ResultSet rs = db.queryAll(sql); 
		         List<GreenRodeBO> list = resultsetToVO(rs);
		         db.closeRs();  
		         db.closeStm();  
		         db.closeConn();  
		         return list;  
		     }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<GreenRodeBO>();
    } 
	
	private List<GreenRodeBO> resultsetToVO(ResultSet rs)
		      throws Exception{
	    List<GreenRodeBO> list=new ArrayList<GreenRodeBO>();
	    while(rs.next()){
	    	GreenRodeBO bo = new GreenRodeBO();
	    	bo.setId(rs.getInt("id")+"");
        	
	    	PatientBO patientBO = new PatientBean().getObjById(rs.getInt("patient_id")+"");
	    	 
	    	bo.setPatient(patientBO.getName());
	    	bo.setPatientPhone(patientBO.getPhone());
	    	bo.setNeedService(rs.getString("patient_note"));
	    	
	    	java.sql.Timestamp time = rs.getTimestamp("create_time");
	    	bo.setCreateTime(DateAndTimeUtil.timestampToString(time));
	    	bo.setState(stateArr[rs.getInt("status")]);
	    	
	        list.add(bo);
	    }
	    return list;
	}

	public GreenRodeBO getObjById(String id){
		DBAccess db = new DBAccess();  
		GreenRodeBO bo = new GreenRodeBO();
		try {
			 if(db.createConn()) {  
		            String sql = "select * from t_express_lane_service where id = "+id;  
		            ResultSet rs = db.queryAll(sql); 
		            while(rs.next()){
		            	bo.setId(rs.getInt("id")+"");
		            	
				    	PatientBO patientBO = new PatientBean().getObjById(rs.getInt("patient_id")+"");
				    	
				    	bo.setPatient(patientBO.getName());
				    	bo.setPatientPhone(patientBO.getPhone());
				    	bo.setNeedService(rs.getString("patient_note"));
				    	
				    	java.sql.Timestamp time = rs.getTimestamp("create_time");
				    	bo.setCreateTime(DateAndTimeUtil.timestampToString(time));
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
	
	public List<GreenRodeBO> getyesterdayList() throws Exception {
		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select * from t_express_lane_service where create_time >= '"+yesterday+" 00:00:00' and create_time < '"+yesterday+" 23:59:59'";
            System.out.println("getyesterdayList sql = "+sql);
            ResultSet rs = db.queryAll(sql); 
            List<GreenRodeBO> list = resultsetToVO(rs);
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
            return list;  
        }  
        return new ArrayList();
	}

	public int getCount() throws Exception {
		return super.getCount("t_express_lane_service");
	}

	public void getDailyData(
			StringBuffer sbDailyZhuanjiahao,StringBuffer sbDailyDuoxueke, 
			StringBuffer sbDailyChuancihuojian,StringBuffer sbDailyAnpaizhuyuan, 
			StringBuffer sbDailyAnpaishousu,StringBuffer sbDailyOther,
			
			StringBuffer sbDailyYizhen, StringBuffer sbDailyBaichengxing, 
			StringBuffer sbDailyTotal
			
			) throws SQLException {
		
		int dailyZhuanjiahao = 0;
		int dailyDuoxueke = 0;
		int dailyChuancihuojian = 0;
		int dailyAnpaizhuyuan = 0;
		int dailyAnpaishousu = 0;
		int dailyOther = 0;
		
		int dailyYizhen = 0;
		int dailyBaichengxing = 0;
		
		int dailyTotal = 0;

		String yesterday = DateUtil.getYesterdayString();
        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
            String sql = "select * from t_express_lane_service where create_time >= '"+yesterday+" 00:00:00' and create_time < '"+yesterday+" 23:59:59'";
            ResultSet rs = db.queryAll(sql); 
            while(rs.next()){
            	++dailyTotal;
            	int parentType = rs.getInt("service_type");
            	if(1==parentType){//绿色通道
            		switch(rs.getInt("type")){
	            		case 0:
	            			++dailyZhuanjiahao;
	            			break;
	            		case 1:
	            			++dailyDuoxueke;
	            			break;
	            		case 2:
	            			++dailyChuancihuojian;
	            			break;
	            		case 3:
	            			++dailyAnpaizhuyuan;
	            			break;
	            		case 4:
	            			++dailyAnpaishousu;
	            			break;	            			
	            		case 5:
	            			++dailyOther;
	            			break;	            			
	            			
	            		default:
	            			break;
            		}
            	}else{
            		switch(rs.getInt("type")){
	            		case 0:
	            			++dailyYizhen;
	            			break;
	            		case 1:
	            			++dailyBaichengxing;
	            			break;
	            		default:
	            			break;
            		}
            	}
    	    }
            sbDailyZhuanjiahao.append(dailyZhuanjiahao);
            sbDailyDuoxueke.append(dailyDuoxueke);
            sbDailyChuancihuojian.append(dailyChuancihuojian);
            sbDailyAnpaizhuyuan.append(dailyAnpaizhuyuan);
            sbDailyAnpaishousu.append(dailyAnpaishousu);
            sbDailyOther.append(dailyOther);
            
            sbDailyYizhen.append(dailyYizhen);
            sbDailyBaichengxing.append(dailyBaichengxing);
            
            sbDailyTotal.append(dailyTotal);
		
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
        }  
	}
	//获取各种count   queryType:0 daily
	public void getManyCountData(
			StringBuffer sbZhuanjiahao,StringBuffer sbDuoxueke, 
			StringBuffer sbChuancihuojian,StringBuffer sbAnpaizhuyuan, 
			StringBuffer sbAnpaishousu,StringBuffer sbOther,
			
			StringBuffer sbYizhen, StringBuffer sbBaichengxing, 
			StringBuffer sbTotal,int queryType
			
			) throws SQLException {
		
		int zhuanjiahao = 0;
		int duoxueke = 0;
		int chuancihuojian = 0;
		int anpaizhuyuan = 0;
		int anpaishousu = 0;
		int other = 0;
		
		int yizhen = 0;
		int baichengxing = 0;
		
		int total = 0;

        DBAccess db = new DBAccess();  
        if(db.createConn()) {  
        	String sql;
        	if(queryType == 0){//daily
        		String yesterday = DateUtil.getYesterdayString();
                sql = "select * from t_express_lane_service where create_time >= '"+yesterday+" 00:00:00' and create_time < '"+yesterday+" 23:59:59'";
        	}else{
        		sql = "select * from t_express_lane_service";
        	}
            ResultSet rs = db.queryAll(sql); 
            while(rs.next()){
            	++total;
            	int parentType = rs.getInt("service_type");
            	if(2==parentType){//义诊、百城行
            		switch(rs.getInt("type")){
	            		case 0:
	            			++yizhen;
	            			break;
	            		case 1:
	            			++baichengxing;
	            			break;
	            		default:
	            			break;
	        		}
            	}else{
            		switch(rs.getInt("type")){
	            		case 0:
	            			++zhuanjiahao;
	            			break;
	            		case 1:
	            			++duoxueke;
	            			break;
	            		case 2:
	            			++chuancihuojian;
	            			break;
	            		case 3:
	            			++anpaizhuyuan;
	            			break;
	            		case 4:
	            			++anpaishousu;
	            			break;	            			
	            		case 5:
	            			++other;
	            			break;	            			
	            			
	            		default:
	            			break;
	        		}
            	}
    	    }
            sbZhuanjiahao.append(zhuanjiahao);
            sbDuoxueke.append(duoxueke);
            sbChuancihuojian.append(chuancihuojian);
            sbAnpaizhuyuan.append(anpaizhuyuan);
            sbAnpaishousu.append(anpaishousu);
            sbOther.append(other);
            
            sbYizhen.append(yizhen);
            sbBaichengxing.append(baichengxing);
            
            sbTotal.append(total);
		
            db.closeRs();  
            db.closeStm();  
            db.closeConn();  
        }  
	}
}
