package com.xeyj.pojo;

public class AdditionalRegisterBO {
	//����ʵ����
	private String id;
	private String doctor;//ҽ��
	private String doctorPhone;//ҽ���ֻ��
	private String doctorDepartment;//ҽ�����ڿ���
	private String doctorHospital;//ҽ������ҽԺ
	private String doctorJobTitle;//ҽ��ְ��
	
	private String patient;//����
	private String patientPhone;//�����ֻ�
	private String state;//״̬
	private String createTime;//�µ�ʱ��
	
	private String medicalRecordId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMedicalRecordId() {
		return medicalRecordId;
	}
	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getDoctorDepartment() {
		return doctorDepartment;
	}
	public void setDoctorDepartment(String doctorDepartment) {
		this.doctorDepartment = doctorDepartment;
	}
	public String getDoctorHospital() {
		return doctorHospital;
	}
	public void setDoctorHospital(String doctorHospital) {
		this.doctorHospital = doctorHospital;
	}
	public String getDoctorJobTitle() {
		return doctorJobTitle;
	}
	public void setDoctorJobTitle(String doctorJobTitle) {
		this.doctorJobTitle = doctorJobTitle;
	}
	
}
