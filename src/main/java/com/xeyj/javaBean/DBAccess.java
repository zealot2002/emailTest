package com.xeyj.javaBean;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.management.Query;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBAccess {
	// ��ݿ���
	private String drv = "com.mysql.jdbc.Driver";

	// //测试
	// //����URL
	// private String url =
	// "jdbc:mysql://rdszbar32zbar32.mysql.rds.aliyuncs.com:3306/mmedi_test_db2";
	// //�û���
	// private String usr = "mmedi_test_user";
	// //����
	// private String pwd = "Mmedi100";

	// 生产
	 // ����URL
//	 private String url = "jdbc:mysql://rdszbar32zbar32.mysql.rds.aliyuncs.com:3306/mmedi_db2?createDatabaseIfNotExist=true";
	 //�û���
//	 private String usr = "mmedi_user";
	 //����
//	 private String pwd = "mmedi100";

	// //本地
////	// ����URL
	private String url = "jdbc:mysql://localhost:3306/health";
//	// �û���
	private String usr = "root";
//	// ����
	private String pwd = "root";

	// ��ݿ����Ӷ���
	private Connection conn = null;
	// ��ݿ���������
	private Statement stm = null;
	// ��ݽ�����
	private ResultSet rs = null;

	public String getDrv() {
		return drv;
		
	}

	public void setDrv(String drv) {
		this.drv = drv;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStm() {
		return stm;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public boolean createConn() {
		boolean b = false;
		try {
			Class.forName(drv).newInstance();
			conn = (Connection) DriverManager.getConnection(url, usr, pwd);
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean update(String sql) {
		boolean b = false;
		try {
			stm = (Statement) conn.createStatement();
			stm.execute(sql);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public void query(String sql) {
		try {
			stm = (Statement) conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int queryCount(String sql) throws SQLException {
		int count = -1;
		stm = (Statement) conn.createStatement();
		rs = stm.executeQuery(sql);
		if (rs.next()) {
			count = rs.getInt(1);
		}

		return count;
	}

	public ResultSet queryAll(String sql) {
		try {
			stm = (Statement) conn.createStatement();
			return stm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean next() {
		boolean b = false;
		try {
			if (rs.next())
				b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public String getValue(String field) {
		String value = null;
		try {
			if (rs != null)
				value = rs.getString(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void closeRs() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeStm() {
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
