package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SYS_T_LOG_INFO")
@NamedQuery(name="SystemTLog.findAll", query="SELECT p FROM SystemTLog p")
public class SystemTLog extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="generator",strategy="uuid.hex")
	@GeneratedValue(generator="generator")
	@Column(name="ID")
	private String id;

	@Column(name="OPT_USER_ID")
	private String optuserid;

	@Column(name="OPT_USER_NAME")
	private String optusername;

	@Column(name="OPT_UNIT_ID")
	private String optunitid;

	@Column(name="OPT_UNIT_NAME")
	private String optunitname;

	@Column(name="OPT_TIME")
	private String opttime;
	
	@Column(name="MODEL_CODE")
	private String modelcode;

	@Column(name="MODEL_NAME")
	private String modelname;

	@Column(name="OPT_TYPE_CODE")
	private String opttypecode;

	@Column(name="OPT_TYPE_NAME")
	private String opttypename;

	@Column(name="OPT_CONTENT")
	private String optcontent;

	@Column(name="ACTION_CLASS_NAME")
	private String actionclassname;

	@Column(name="ACTION_FUNCTION_NAME")
	private String actionfunctionname;

	@Column(name="REQUEST_URI")
	private String requesturi;
	
	@Column(name="FLAG")
	private String flag;
	
	@Column(name="BELONG_SYSTEM_CODE")
	private String belongsystemcode;
	
	@Column(name="BELONG_SYSTEM_NAME")
	private String belongsystemname;
	
	public String getBelongsystemcode() {
		return belongsystemcode;
	}

	public void setBelongsystemcode(String belongsystemcode) {
		this.belongsystemcode = belongsystemcode;
	}

	public String getBelongsystemname() {
		return belongsystemname;
	}

	public void setBelongsystemname(String belongsystemname) {
		this.belongsystemname = belongsystemname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOptuserid() {
		return optuserid;
	}

	public void setOptuserid(String optuserid) {
		this.optuserid = optuserid;
	}

	public String getOptusername() {
		return optusername;
	}

	public void setOptusername(String optusername) {
		this.optusername = optusername;
	}

	public String getOptunitid() {
		return optunitid;
	}

	public void setOptunitid(String optunitid) {
		this.optunitid = optunitid;
	}

	public String getOptunitname() {
		return optunitname;
	}

	public void setOptunitname(String optunitname) {
		this.optunitname = optunitname;
	}

	public String getOpttime() {
		return opttime;
	}

	public void setOpttime(String opttime) {
		this.opttime = opttime;
	}

	public String getModelcode() {
		return modelcode;
	}

	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}


	public String getOpttypecode() {
		return opttypecode;
	}

	public void setOpttypecode(String opttypecode) {
		this.opttypecode = opttypecode;
	}

	public String getOpttypename() {
		return opttypename;
	}

	public void setOpttypename(String opttypename) {
		this.opttypename = opttypename;
	}

	public String getOptcontent() {
		return optcontent;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public void setOptcontent(String optcontent) {
		this.optcontent = optcontent;
	}

	public String getActionclassname() {
		return actionclassname;
	}

	public void setActionclassname(String actionclassname) {
		this.actionclassname = actionclassname;
	}

	public String getActionfunctionname() {
		return actionfunctionname;
	}

	public void setActionfunctionname(String actionfunctionname) {
		this.actionfunctionname = actionfunctionname;
	}

	public String getRequesturi() {
		return requesturi;
	}

	public void setRequesturi(String requesturi) {
		this.requesturi = requesturi;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public SystemTLog() {
	}
}
