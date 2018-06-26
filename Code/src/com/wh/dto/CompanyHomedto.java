package com.wh.dto;

import java.util.List;

import com.wh.model.T03;
import com.wh.model.T05;
import com.wh.model.T07;

public class CompanyHomedto {
	
	private String cName;//公司名称
	private String cEClass;//经济性质
	private String cMClass;//行业类别
	private String buildTime;//成立日期
	private String legalPerson;//法人代表
	private String address;//公司地址
	private String securityRP;//安全责任人
	private String securityMP;//安全管理人
	private List<T05> cLicenses;//企业资质
	private List<T07> pLicenses;//人员资质
	private List<T03> sequs;//特种设备
	
	
	public List<T03> getSequs() {
		return sequs;
	}
	public void setSequs(List<T03> sequs) {
		this.sequs = sequs;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcEClass() {
		return cEClass;
	}
	public void setcEClass(String cEClass) {
		this.cEClass = cEClass;
	}
	public String getcMClass() {
		return cMClass;
	}
	public void setcMClass(String cMClass) {
		this.cMClass = cMClass;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSecurityRP() {
		return securityRP;
	}
	public void setSecurityRP(String securityRP) {
		this.securityRP = securityRP;
	}
	public String getSecurityMP() {
		return securityMP;
	}
	public void setSecurityMP(String securityMP) {
		this.securityMP = securityMP;
	}
	public List<T05> getcLicenses() {
		return cLicenses;
	}
	public void setcLicenses(List<T05> cLicenses) {
		this.cLicenses = cLicenses;
	}
	public List<T07> getpLicenses() {
		return pLicenses;
	}
	public void setpLicenses(List<T07> pLicenses) {
		this.pLicenses = pLicenses;
	}
	 
	 
	 
}
