package com.wh.model;

import java.util.Date;

public class T05 {
    private Integer f0501;

    private Integer f0101;

    private String f0502;

    private String f0503;

    private Date f0504;

    private String f0505;

    private T01 t01;
    
    public T01 getT01() {
		return t01;
	}
	public void setT01(T01 t01) {
		this.t01 = t01;
	}
	public T05(){}
    public T05(Integer f0501, Integer f0101, String f0502, String f0503, Date f0504, String f0505) {
		super();
		this.f0501 = f0501;
		this.f0101 = f0101;
		this.f0502 = f0502;
		this.f0503 = f0503;
		this.f0504 = f0504;
		this.f0505 = f0505;
	}

	public Integer getF0501() {
        return f0501;
    }

    public void setF0501(Integer f0501) {
        this.f0501 = f0501;
    }

    public Integer getF0101() {
        return f0101;
    }

    public void setF0101(Integer f0101) {
        this.f0101 = f0101;
    }

    public String getF0502() {
        return f0502;
    }

    public void setF0502(String f0502) {
        this.f0502 = f0502 == null ? null : f0502.trim();
    }

    public String getF0503() {
        return f0503;
    }

    public void setF0503(String f0503) {
        this.f0503 = f0503 == null ? null : f0503.trim();
    }

    public Date getF0504() {
        return f0504;
    }

    public void setF0504(Date f0504) {
        this.f0504 = f0504;
    }

    public String getF0505() {
        return f0505;
    }

    public void setF0505(String f0505) {
        this.f0505 = f0505 == null ? null : f0505.trim();
    }
}