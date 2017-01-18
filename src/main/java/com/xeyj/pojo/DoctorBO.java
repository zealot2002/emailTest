package com.xeyj.pojo;

import java.util.ArrayList;
import java.util.List;

public class DoctorBO {
	private String id;
	private String name;
	private String phone;//
	private String hospital;//ҽԺ
	private String department;//����
	private String state;//���״̬
	
	private String jobDuty;//ְ��
	private String jobTitle;//ְ��
	private String idCard;//rivate
	private String expertise;//�ó�����
	private String username;//�û���
	private String registerTime;//
	private String barCode;//��ά��
	private String sex;
	
	private List<String> smallImgList = new ArrayList<String>();
	private List<String> bigImgList = new ArrayList<String>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJobDuty() {
		return jobDuty;
	}
	public void setJobDuty(String jobDuty) {
		this.jobDuty = jobDuty;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}




	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public List<String> getSmallImgList() {
		return smallImgList;
	}
	public void setSmallImgList(List<String> smallImgList) {
		this.smallImgList = smallImgList;
	}
	public List<String> getBigImgList() {
		return bigImgList;
	}
	public void setBigImgList(List<String> bigImgList) {
		this.bigImgList = bigImgList;
	}


	
	
}
