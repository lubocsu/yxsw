package com.upsoft.system.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class TableColCommnetsBean implements Serializable {

	private static final long serialVersionUID = 8340945833557423914L;
	private String table_name;
	private String column_name;
	private String comments;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
