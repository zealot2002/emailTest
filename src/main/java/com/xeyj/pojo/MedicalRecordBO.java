package com.xeyj.pojo;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordBO {
	//����ʵ����
	
	private String id;
	private String diseaseName;//�������
	private String hospital;//����ҽԺ
	private String department;//���ڿ���
	private String visitTime;//����ʱ��
	
	//info
	private String description;//��������
	private String deagnosticInfo;//�����Ϣ
	private String medication;//��ҩ���
	
	private List<String> smallImgList = new ArrayList<String>();
	private List<String> bigImgList = new ArrayList<String>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
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
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeagnosticInfo() {
		return deagnosticInfo;
	}
	public void setDeagnosticInfo(String deagnosticInfo) {
		this.deagnosticInfo = deagnosticInfo;
	}
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
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
