package com.wh.model;

import java.util.Date;

public class T12 {
    private Integer f1201;

    private String f1202;

    private Date f1204;

    private String f1205;

    private String f1203;

    
    
    
    public T12() {
		super();
	}

	public T12(Integer f1201, String f1202, Date f1204, String f1205, String f1203) {
		super();
		this.f1201 = f1201;
		this.f1202 = f1202;
		this.f1204 = f1204;
		this.f1205 = f1205;
		this.f1203 = f1203;
	}

	public Integer getF1201() {
        return f1201;
    }

    public void setF1201(Integer f1201) {
        this.f1201 = f1201;
    }

    public String getF1202() {
        return f1202;
    }

    public void setF1202(String f1202) {
        this.f1202 = f1202 == null ? null : f1202.trim();
    }

    public Date getF1204() {
        return f1204;
    }

    public void setF1204(Date f1204) {
        this.f1204 = f1204;
    }

    public String getF1205() {
        return f1205;
    }

    public void setF1205(String f1205) {
        this.f1205 = f1205 == null ? null : f1205.trim();
    }

    public String getF1203() {
        return f1203;
    }

    public void setF1203(String f1203) {
        this.f1203 = f1203 == null ? null : f1203.trim();
    }
}