package com.wh.model;

import java.util.Date;

public class T10 {
    private Integer f1001;

    private Integer f0101;

    private String f1002;

    private Date f1003;

    private String f1004;

    private String f1005;

    private String f1006;

    private Integer f1007;

    private String f1008;

    private Date f1009;

    private Date f1010;
    
    private T01 t01;
    
    
    public T01 getT01() {
		return t01;
	}

	public void setT01(T01 t01) {
		this.t01 = t01;
	}

	public T10() {
		super();
	}

	public T10(Integer f1001, Integer f0101, String f1002, Date f1003, String f1004, String f1005, String f1006,
			Integer f1007, String f1008, Date f1009, Date f1010) {
		super();
		this.f1001 = f1001;
		this.f0101 = f0101;
		this.f1002 = f1002;
		this.f1003 = f1003;
		this.f1004 = f1004;
		this.f1005 = f1005;
		this.f1006 = f1006;
		this.f1007 = f1007;
		this.f1008 = f1008;
		this.f1009 = f1009;
		this.f1010 = f1010;
	}

	public Integer getF1001() {
        return f1001;
    }

    public void setF1001(Integer f1001) {
        this.f1001 = f1001;
    }

    public Integer getF0101() {
        return f0101;
    }

    public void setF0101(Integer f0101) {
        this.f0101 = f0101;
    }

    public String getF1002() {
        return f1002;
    }

    public void setF1002(String f1002) {
        this.f1002 = f1002 == null ? null : f1002.trim();
    }

    public Date getF1003() {
        return f1003;
    }

    public void setF1003(Date f1003) {
        this.f1003 = f1003;
    }

    public String getF1004() {
        return f1004;
    }

    public void setF1004(String f1004) {
        this.f1004 = f1004 == null ? null : f1004.trim();
    }

    public String getF1005() {
        return f1005;
    }

    public void setF1005(String f1005) {
        this.f1005 = f1005 == null ? null : f1005.trim();
    }

    public String getF1006() {
        return f1006;
    }

    public void setF1006(String f1006) {
        this.f1006 = f1006 == null ? null : f1006.trim();
    }

    public Integer getF1007() {
        return f1007;
    }

    public void setF1007(Integer f1007) {
        this.f1007 = f1007;
    }

    public String getF1008() {
        return f1008;
    }

    public void setF1008(String f1008) {
        this.f1008 = f1008 == null ? null : f1008.trim();
    }

    public Date getF1009() {
        return f1009;
    }

    public void setF1009(Date f1009) {
        this.f1009 = f1009;
    }

    public Date getF1010() {
        return f1010;
    }

    public void setF1010(Date f1010) {
        this.f1010 = f1010;
    }
}