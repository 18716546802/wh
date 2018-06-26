package com.wh.model;

import java.util.Date;

public class T04 {
    private Integer f0401;

    private Integer f0301;

    private String f0402;

    private Date f0403;

    private String f0404;

    private String f0405;

    private String f0406;

    private Date f0407;

    private String f0408;
    
    private T03 t03;
    
    public T03 getT03() {
		return t03;
	}
	public void setT03(T03 t03) {
		this.t03 = t03;
	}
	public T04(){}
    public T04(Integer f0401, Integer f0301, String f0402, Date f0403, String f0404, String f0405, String f0406,
			Date f0407, String f0408) {
		super();
		this.f0401 = f0401;
		this.f0301 = f0301;
		this.f0402 = f0402;
		this.f0403 = f0403;
		this.f0404 = f0404;
		this.f0405 = f0405;
		this.f0406 = f0406;
		this.f0407 = f0407;
		this.f0408 = f0408;
	}

	public Integer getF0401() {
        return f0401;
    }

    public void setF0401(Integer f0401) {
        this.f0401 = f0401;
    }

    public Integer getF0301() {
        return f0301;
    }

    public void setF0301(Integer f0301) {
        this.f0301 = f0301;
    }

    public String getF0402() {
        return f0402;
    }

    public void setF0402(String f0402) {
        this.f0402 = f0402 == null ? null : f0402.trim();
    }

    public Date getF0403() {
        return f0403;
    }

    public void setF0403(Date f0403) {
        this.f0403 = f0403;
    }

    public String getF0404() {
        return f0404;
    }

    public void setF0404(String f0404) {
        this.f0404 = f0404 == null ? null : f0404.trim();
    }

    public String getF0405() {
        return f0405;
    }

    public void setF0405(String f0405) {
        this.f0405 = f0405 == null ? null : f0405.trim();
    }

    public String getF0406() {
        return f0406;
    }

    public void setF0406(String f0406) {
        this.f0406 = f0406 == null ? null : f0406.trim();
    }

    public Date getF0407() {
        return f0407;
    }

    public void setF0407(Date f0407) {
        this.f0407 = f0407;
    }

    public String getF0408() {
        return f0408;
    }

    public void setF0408(String f0408) {
        this.f0408 = f0408 == null ? null : f0408.trim();
    }
}