package com.wh.model;

import java.util.Date;

public class T07 {
    private Integer f0701;

    private Integer f0101;

    private String f0702;

    private String f0703;

    private Date f0704;

    private String f0705;

    private String f0706;

    private String f0707;

    private String f0708;

    private T01 t01;
    
    public T01 getT01() {
		return t01;
	}

	public void setT01(T01 t01) {
		this.t01 = t01;
	}

	public T07() {
		super();
	}

	public T07(Integer f0701, Integer f0101, String f0702, String f0703, Date f0704, String f0705, String f0706,
			String f0707, String f0708) {
		super();
		this.f0701 = f0701;
		this.f0101 = f0101;
		this.f0702 = f0702;
		this.f0703 = f0703;
		this.f0704 = f0704;
		this.f0705 = f0705;
		this.f0706 = f0706;
		this.f0707 = f0707;
		this.f0708 = f0708;
	}

	public Integer getF0701() {
        return f0701;
    }

    public void setF0701(Integer f0701) {
        this.f0701 = f0701;
    }

    public Integer getF0101() {
        return f0101;
    }

    public void setF0101(Integer f0101) {
        this.f0101 = f0101;
    }

    public String getF0702() {
        return f0702;
    }

    public void setF0702(String f0702) {
        this.f0702 = f0702 == null ? null : f0702.trim();
    }

    public String getF0703() {
        return f0703;
    }

    public void setF0703(String f0703) {
        this.f0703 = f0703 == null ? null : f0703.trim();
    }

    public Date getF0704() {
        return f0704;
    }

    public void setF0704(Date f0704) {
        this.f0704 = f0704;
    }

    public String getF0705() {
        return f0705;
    }

    public void setF0705(String f0705) {
        this.f0705 = f0705 == null ? null : f0705.trim();
    }

    public String getF0706() {
        return f0706;
    }

    public void setF0706(String f0706) {
        this.f0706 = f0706 == null ? null : f0706.trim();
    }

    public String getF0707() {
        return f0707;
    }

    public void setF0707(String f0707) {
        this.f0707 = f0707 == null ? null : f0707.trim();
    }

    public String getF0708() {
        return f0708;
    }

    public void setF0708(String f0708) {
        this.f0708 = f0708 == null ? null : f0708.trim();
    }
}